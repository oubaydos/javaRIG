package io.javarig.generator;

public class BooleanGenerator implements Generator{
    @Override
    public Boolean generate() {
        return random.nextBoolean();
    }
}
