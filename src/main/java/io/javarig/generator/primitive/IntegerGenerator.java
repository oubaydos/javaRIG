package io.javarig.generator.primitive;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.AbstractTypeGenerator;

import java.lang.reflect.Type;

public class IntegerGenerator extends AbstractTypeGenerator {
    public IntegerGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Integer generate() {
        return getRandom().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
