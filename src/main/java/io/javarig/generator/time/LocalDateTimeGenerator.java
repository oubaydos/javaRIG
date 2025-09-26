package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.time.*;

public class LocalDateTimeGenerator extends BaseDateTimeGenerator<LocalDateTime> {
    public LocalDateTimeGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    protected LocalDateTime convert(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toLocalDateTime();
    }
}
