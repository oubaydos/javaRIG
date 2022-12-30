package io.javarig;

import com.google.common.primitives.Primitives;
import io.javarig.exception.JavaRIGInternalException;
import io.javarig.generator.TypeGenerator;
import lombok.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import static io.javarig.TypeEnum.*;

public class TypeGeneratorFactory {

    /**
     * gets a TypeEnum instance and prepare its generator
     *
     * @param type                    Type instance to get its associated TypeEnum
     * @param randomInstanceGenerator instance that will be used to generate nested objects
     * @return TypeEnum instance associated to the type object with generator prepared with necessary objects
     */
    public TypeGenerator getGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        try {
            return createGeneratorInstance(type, randomInstanceGenerator);
        } catch (ReflectiveOperationException e) {
            throw new JavaRIGInternalException(e);
        }
    }

    /**
     * gets the rawType if type is instance of parameterizedType, returns type otherwise
     *
     * @param type Type instance
     * @return the rawType of the type object
     */
    private Class<?> getRawType(Type type) {
        Type rawType = type;
        if (type instanceof ParameterizedType parameterizedType) {
            rawType = parameterizedType.getRawType();
        }
        return Primitives.wrap((Class<?>) rawType);
    }

    /**
     * gets the TypeEnum associated to the type object
     */
    private TypeEnum getTypeEnumFromType(Type type) {
        //we only need the raw type, for example : if we have the type of List<Sting> we only need now the class of List
        Class<?> rawType = getRawType(type);
        return Arrays.stream(TypeEnum.values())
                .filter(tEnum -> tEnum.type != null && tEnum.type.equals(rawType))
                .findFirst()
                .orElseGet(() -> getTypeEnumForUnmatchedTypes(rawType));
    }

    @NonNull
    private TypeEnum getTypeEnumForUnmatchedTypes(Class<?> type) {
        if (type.isArray()) {
            return ARRAY;
        }
        if (type.isEnum())
            return ENUM;
        return OBJECT;
    }

    public TypeGenerator createGeneratorInstance(Type type, RandomInstanceGenerator randomInstanceGenerator) throws ReflectiveOperationException {
        TypeEnum typeEnum = getTypeEnumFromType(type);
        return typeEnum.generatorClass.getConstructor(Type.class, RandomInstanceGenerator.class)
                .newInstance(type, randomInstanceGenerator);
    }
}
