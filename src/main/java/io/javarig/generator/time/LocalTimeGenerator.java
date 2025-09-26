package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class LocalTimeGenerator extends BaseDateTimeGenerator<LocalTime> {
    public LocalTimeGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    protected LocalTime convert(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toLocalTime();
    }
}
