package io.javarig.generator;

public class BooleanGenerator extends AbstractGenerator{
    @Override
    public Boolean generate() {
        return getRandom().nextBoolean();
    }
}
