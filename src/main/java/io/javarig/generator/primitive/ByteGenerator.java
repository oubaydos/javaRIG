package io.javarig.generator.primitive;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.TypeGenerator;

import java.lang.reflect.Type;

public class ByteGenerator extends TypeGenerator {

    public ByteGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Byte generate() {
        byte[] bytes = new byte[1];
        getRandom().nextBytes(bytes);
        return bytes[0];
    }
}
