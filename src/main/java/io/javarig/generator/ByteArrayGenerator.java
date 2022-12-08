package io.javarig.generator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ByteArrayGenerator extends AbstractTypeGenerator implements CollectionGenerator {
    private int maxSizeExclusive = DEFAULT_MAX_SIZE_INCLUSIVE;
    private int minSizeInclusive = DEFAULT_MIN_SIZE_INCLUSIVE;

    @Override
    public byte[] generate() {
        int size = getRandom().nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        byte[] bytes = new byte[size];
        getRandom().nextBytes(bytes);
        return bytes;
    }
}
