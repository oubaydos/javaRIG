package io.javarig.generator;

public class ShortGenerator extends AbstractTypeGenerator {
    @Override
    public Short generate() {
        return (short) getRandom().nextInt(Short.MIN_VALUE, Short.MAX_VALUE + 1);
    }
}
