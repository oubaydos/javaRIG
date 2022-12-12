package io.javarig.generator;


import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;

public class BooleanGenerator extends AbstractTypeGenerator {
    public BooleanGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Boolean generate() {
        return getRandom().nextBoolean();
    }
}
