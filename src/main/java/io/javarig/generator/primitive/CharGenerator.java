package io.javarig.generator.primitive;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.TypeGenerator;

import java.lang.reflect.Type;

public class CharGenerator extends TypeGenerator {
    public CharGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Character generate() {
        return (char) getRandom().nextInt(Character.MAX_VALUE);
    }
}
