package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;

public class FloatGenerator extends AbstractTypeGenerator {
    public FloatGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Float generate() {
        return getRandom().nextFloat(0, Float.MAX_VALUE + 1);
    }
}
