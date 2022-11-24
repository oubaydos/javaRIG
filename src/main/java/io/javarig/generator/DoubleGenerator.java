package io.javarig.generator;

public class DoubleGenerator extends AbstractTypeGenerator {
    @Override
    public Double generate() {
        return getRandom().nextDouble(0, Double.MAX_VALUE);
    }
}
