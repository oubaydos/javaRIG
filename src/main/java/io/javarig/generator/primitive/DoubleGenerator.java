package io.javarig.generator.primitive;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.AbstractTypeGenerator;

import java.lang.reflect.Type;

public class DoubleGenerator extends AbstractTypeGenerator {
    public DoubleGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Double generate() {
        return getRandom().nextDouble(0, Double.MAX_VALUE);
    }
}
