package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;

@Getter
@Setter
public class ByteArrayGenerator extends AbstractTypeGenerator implements CollectionGenerator {
    private int maxSizeExclusive = DEFAULT_MAX_SIZE_EXCLUSIVE;
    private int minSizeInclusive = DEFAULT_MIN_SIZE_INCLUSIVE;

    public ByteArrayGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public byte[] generate() {
        int size = getRandom().nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        byte[] bytes = new byte[size];
        getRandom().nextBytes(bytes);
        return bytes;
    }
}
