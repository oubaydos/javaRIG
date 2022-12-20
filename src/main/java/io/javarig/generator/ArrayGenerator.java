package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import io.javarig.exception.InstanceGenerationException;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.List;

@Setter
@Getter
public class ArrayGenerator<T> extends AbstractTypeGenerator implements TypeGenerator, CollectionGenerator {
    private int minSizeInclusive = DEFAULT_MIN_SIZE_INCLUSIVE;
    private int maxSizeExclusive = DEFAULT_MAX_SIZE_EXCLUSIVE;

    public ArrayGenerator(Class<T> type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }


    @Override
    @SuppressWarnings("unchecked")
    public T[] generate() throws InstanceGenerationException {
        Class<?> arrayParameterType = ((Class<?>) getType()).componentType();
        List<Object> objectList = getRandomInstanceGenerator()
                .generate(List.class, getMinSizeInclusive(), getMaxSizeExclusive(), arrayParameterType);
        T[] o = (T[]) Array.newInstance(arrayParameterType, 0);
        return objectList.toArray(o);
    }

    public static <T> ArrayGenerator<T> create(Class<T> type, RandomInstanceGenerator randomInstanceGenerator) {
        return new ArrayGenerator<>(type, randomInstanceGenerator);
    }
}
