package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.TypeGenerator;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.Date;

public class DateGenerator extends TypeGenerator {
    public DateGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Date generate() {
        return Date.from(getRandomInstanceGenerator().generate(Instant.class));
    }
}
