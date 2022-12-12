package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;

public class ShortGenerator extends AbstractTypeGenerator {
    public ShortGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Short generate() {
        return (short) getRandom().nextInt(Short.MIN_VALUE, Short.MAX_VALUE + 1);
    }
}
