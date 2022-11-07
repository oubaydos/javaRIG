package io.javarig.generator;

public class LongGenerator implements Generator{
    @Override
    public Long generate() {
        return random.nextLong(0, Long.MAX_VALUE);
    }
}
