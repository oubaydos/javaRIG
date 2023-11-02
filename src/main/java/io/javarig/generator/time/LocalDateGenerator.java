package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.TypeGenerator;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

public class LocalDateGenerator extends TypeGenerator {
    public LocalDateGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public LocalDate generate() {
        return ((Instant) getRandomInstanceGenerator().generate(Instant.class)).atZone(UTC).toLocalDate();
    }
}
