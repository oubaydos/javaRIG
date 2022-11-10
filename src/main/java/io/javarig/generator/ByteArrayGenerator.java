package io.javarig.generator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ByteArrayGenerator implements CollectionGenerator {
    private int maxSizeExclusive = 15;
    private int minSizeInclusive = 5;

    @Override
    public byte[] generate() {
        int size = random.nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        return bytes;
    }
}
