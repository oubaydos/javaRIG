package io.javarig.generator.primitive;


import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.AbstractTypeGenerator;

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
