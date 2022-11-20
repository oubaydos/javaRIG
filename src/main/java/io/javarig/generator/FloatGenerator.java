package io.javarig.generator;

public class FloatGenerator extends AbstractGenerator {
    @Override
    public Float generate() {
        return getRandom().nextFloat(0, Float.MAX_VALUE + 1);
    }
}
