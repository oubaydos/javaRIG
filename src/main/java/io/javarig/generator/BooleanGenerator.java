package io.javarig.generator;

public class BooleanGenerator extends AbstractTypeGenerator {
    @Override
    public Boolean generate() {
        return getRandom().nextBoolean();
    }
}
