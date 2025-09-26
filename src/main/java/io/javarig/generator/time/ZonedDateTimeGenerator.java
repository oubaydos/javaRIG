package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;

public class ZonedDateTimeGenerator extends BaseDateTimeGenerator<ZonedDateTime> {
    public ZonedDateTimeGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    protected ZonedDateTime convert(ZonedDateTime zonedDateTime) {
        return zonedDateTime;
    }
}
