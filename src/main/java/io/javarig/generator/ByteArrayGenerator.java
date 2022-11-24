package io.javarig.generator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ByteArrayGenerator extends AbstractTypeGenerator implements CollectionGenerator {
    private int maxSizeExclusive = 15;
    private int minSizeInclusive = 5;

    @Override
    public byte[] generate() {
        int size = getRandom().nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        byte[] bytes = new byte[size];
        getRandom().nextBytes(bytes);
        return bytes;
    }
}
