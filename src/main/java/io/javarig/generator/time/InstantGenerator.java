package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.TypeGenerator;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

public class InstantGenerator extends TypeGenerator {

    private final Instant MIN_INSTANT = Instant.ofEpochMilli(0);
    private final Instant MAX_INSTANT = LocalDate.of(2100, 12, 31).atStartOfDay(UTC).toInstant();

    public InstantGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Instant generate() {
        return Instant.ofEpochMilli(getRandom().nextLong(MIN_INSTANT.toEpochMilli(), MAX_INSTANT.toEpochMilli()));
    }
}
