package io.javarig;

import com.google.common.primitives.Primitives;
import io.javarig.exception.JavaRIGInternalException;
import io.javarig.generator.*;
import io.javarig.generator.collection.list.ArrayListGenerator;
import io.javarig.generator.collection.map.HashMapGenerator;
import io.javarig.generator.collection.map.TreeMapGenerator;
import io.javarig.generator.primitive.*;
import lombok.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;


public enum TypeEnum {

    INTEGER(Integer.class, IntegerGenerator.class),
    STRING(String.class, StringGenerator.class),
    BYTE(Byte.class, ByteGenerator.class),
    BYTE_ARRAY(Byte[].class, ByteArrayGenerator.class),
    PRIMITIVE_BYTE_ARRAY(byte[].class, ByteArrayGenerator.class),
    SHORT(Short.class, ShortGenerator.class),
    LONG(Long.class, LongGenerator.class),
    DOUBLE(Double.class, DoubleGenerator.class),
    FLOAT(Float.class, FloatGenerator.class),
    BOOLEAN(Boolean.class, BooleanGenerator.class),
    CHAR(Character.class, CharGenerator.class),
    INSTANT(Instant.class, InstantGenerator.class),
    DATE(Date.class, DateGenerator.class),
    LOCAL_DATE(LocalDate.class, LocalDateGenerator.class),
    MAP(Map.class, HashMapGenerator.class),
    HASH_MAP(HashMap.class, HashMapGenerator.class),
    TREE_MAP(TreeMap.class, TreeMapGenerator.class),
    LIST(List.class, ArrayListGenerator.class),
    ARRAY_LIST(ArrayList.class, ArrayListGenerator.class),
    ENUM(null, EnumGenerator.class),
    ARRAY(null, ArrayGenerator.class),
    OBJECT(null, ObjectGenerator.class);

    final Type type;
    final Class<? extends TypeGenerator> generatorClass;

    TypeEnum(Type type, Class<? extends TypeGenerator> generatorClass) {
        this.type = type;
        this.generatorClass = generatorClass;
    }

    /**
     * gets a TypeEnum instance and prepare its generator
     *
     * @param type                    Type instance to get its associated TypeEnum
     * @param randomInstanceGenerator instance that will be used to generate nested objects
     * @return TypeEnum instance associated to the type object with generator prepared with necessary objects
     */
    public static TypeGenerator getGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        TypeEnum typeEnum = getTypeEnumFromType(type);
        return typeEnum.createGeneratorInstance(type, randomInstanceGenerator);
    }

    /**
     * gets the rawType if type is instance of parameterizedType, returns type otherwise
     *
     * @param type Type instance
     * @return the rawType of the type object
     */
    private static Class<?> getRawType(Type type) {
        Type rawType = type;
        if (type instanceof ParameterizedType parameterizedType) {
            rawType = parameterizedType.getRawType();
        }
        return Primitives.wrap((Class<?>) rawType);
    }

    /**
     * gets the TypeEnum associated to the type object
     */
    private static TypeEnum getTypeEnumFromType(Type type) {
        //we only need the raw type, for example : if we have the type of List<Sting> we only need now the class of List
        Class<?> rawType = getRawType(type);
        return Arrays.stream(TypeEnum.values())
                .filter(tEnum -> tEnum.type != null && tEnum.type.equals(rawType))
                .findFirst()
                .orElseGet(() -> getTypeEnumForUnmatchedTypes(rawType));
    }

    @NonNull
    private static TypeEnum getTypeEnumForUnmatchedTypes(Class<?> type) {
        if (type.isArray()) {
            return ARRAY;
        }
        if (type.isEnum())
            return ENUM;
        return OBJECT;
    }

    public TypeGenerator createGeneratorInstance(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        try {
            return generatorClass.getConstructor(Type.class, RandomInstanceGenerator.class)
                    .newInstance(type, randomInstanceGenerator);
        } catch (ReflectiveOperationException e) {
            throw new JavaRIGInternalException(e);
        }
    }
}
