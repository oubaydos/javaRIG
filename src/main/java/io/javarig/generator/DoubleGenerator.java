package io.javarig.generator;

public class DoubleGenerator implements Generator{
    @Override
    public Double generate() {
        return random.nextDouble(0, Double.MAX_VALUE);
    }
}
