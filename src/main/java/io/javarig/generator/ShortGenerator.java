package io.javarig.generator;

public class ShortGenerator implements Generator {
    @Override
    public Short generate() {
        return (short) random.nextInt(0, Short.MAX_VALUE + 1);
    }
}
