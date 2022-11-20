package io.javarig.generator;

public class LongGenerator extends AbstractGenerator{
    @Override
    public Long generate() {
        return getRandom().nextLong(0, Long.MAX_VALUE);
    }
}
