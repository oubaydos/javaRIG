package io.javarig.generator;

public class DoubleGenerator extends AbstractGenerator{
    @Override
    public Double generate() {
        return getRandom().nextDouble(0, Double.MAX_VALUE);
    }
}
