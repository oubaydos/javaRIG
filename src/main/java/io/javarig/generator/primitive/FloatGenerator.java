package io.javarig.generator.primitive;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.TypeGenerator;

import java.lang.reflect.Type;

public class FloatGenerator extends TypeGenerator {
    public FloatGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Float generate() {
        return getRandom().nextFloat(0, Float.MAX_VALUE + 1);
    }
}
