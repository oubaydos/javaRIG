package io.javarig.generator;

public class LongGenerator extends AbstractTypeGenerator {
    @Override
    public Long generate() {
        return getRandom().nextLong(Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
