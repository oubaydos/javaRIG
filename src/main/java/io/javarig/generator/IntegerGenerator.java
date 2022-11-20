package io.javarig.generator;

public class IntegerGenerator extends AbstractGenerator{
    @Override
    public Integer generate() {
        return getRandom().nextInt(0, Integer.MAX_VALUE);
    }
}
