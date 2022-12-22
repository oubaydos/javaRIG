package io.javarig.generator.primitive;


import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.TypeGenerator;

import java.lang.reflect.Type;

public class BooleanGenerator extends TypeGenerator {
    public BooleanGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Boolean generate() {
        return getRandom().nextBoolean();
    }
}
