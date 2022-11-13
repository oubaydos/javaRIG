package io.javarig.generator;

public class FloatGenerator implements Generator {
    @Override
    public Float generate() {
        return random.nextFloat(0, Float.MAX_VALUE + 1);
    }
}
