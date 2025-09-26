package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateGenerator extends BaseDateTimeGenerator<Date> {
    public DateGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    protected Date convert(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }
}
