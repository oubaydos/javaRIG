package io.javarig.generator;

public class IntegerGenerator implements Generator{
    @Override
    public Integer generate() {
        return random.nextInt(0, Integer.MAX_VALUE);
    }
}
