package io.javarig.generator.primitive;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.TypeGenerator;

import java.lang.reflect.Type;

public class IntegerGenerator extends TypeGenerator {
    public IntegerGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Integer generate() {
        return getRandom().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
