package io.javarig.generator;

public class ShortGenerator extends AbstractGenerator {
    @Override
    public Short generate() {
        return (short) getRandom().nextInt(0, Short.MAX_VALUE + 1);
    }
}
