package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.Date;

public class DateGenerator extends AbstractTypeGenerator {
    public DateGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Date generate() {
        return Date.from(getRandomInstanceGenerator().generate(Instant.class));
    }
}
