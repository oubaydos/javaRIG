package io.javarig.generator;

public class IntegerGenerator extends AbstractTypeGenerator {
    @Override
    public Integer generate() {
        return getRandom().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
