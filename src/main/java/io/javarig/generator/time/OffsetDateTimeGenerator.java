package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class OffsetDateTimeGenerator extends BaseDateTimeGenerator<OffsetDateTime> {
    public OffsetDateTimeGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    protected OffsetDateTime convert(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toOffsetDateTime();
    }
}
