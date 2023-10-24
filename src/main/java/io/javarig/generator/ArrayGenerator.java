package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import io.javarig.exception.InstanceGenerationException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.List;

@Setter
@Getter
public class ArrayGenerator extends TypeGenerator {

    public ArrayGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }
    @Override
    public Object generate() throws InstanceGenerationException {
        Class<?> arrayParameterType = ((Class<?>) getType()).componentType();
        if (arrayParameterType.isPrimitive()) {
            return generatePrimitiveArray(arrayParameterType);
        } else {
            return generateArray(arrayParameterType);
        }
    }

    private Object generatePrimitiveArray(Class<?> primitiveType) {
        Class<?> wrapperType = ClassUtils.primitiveToWrapper(primitiveType);
        return ArrayUtils.toPrimitive(generateArray(wrapperType));
    }

    private Object[] generateArray(Class<?> arrayParameterType) {
        List<Object> objectList = getRandomInstanceGenerator()
                .generate(List.class, getConfig().getMinSizeInclusive(), getConfig().getMaxSizeExclusive(), arrayParameterType);
        Object[] arrayInstance = (Object[]) Array.newInstance(arrayParameterType, 0);
        arrayInstance = objectList.toArray(arrayInstance);
        return arrayInstance;
    }
}
