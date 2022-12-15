package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;

public class LongGenerator extends AbstractTypeGenerator {
    public LongGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Long generate() {
        return getRandom().nextLong(Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
