package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class LocalDateGenerator extends BaseDateTimeGenerator<LocalDate> {
    public LocalDateGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    protected LocalDate convert(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toLocalDate();
    }
}
