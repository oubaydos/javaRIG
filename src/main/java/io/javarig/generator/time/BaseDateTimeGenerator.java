package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.TypeGenerator;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

public abstract class BaseDateTimeGenerator<T> extends TypeGenerator {
    public BaseDateTimeGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public final T generate() {
        return convert(((Instant) getRandomInstanceGenerator().generate(Instant.class)).atZone(UTC));
    }
    protected abstract T convert(ZonedDateTime zonedDateTime);
}
