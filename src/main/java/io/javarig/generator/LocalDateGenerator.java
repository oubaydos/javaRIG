package io.javarig.generator;

import java.time.Instant;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

public class LocalDateGenerator implements Generator {
    @Override
    public LocalDate generate() {
        return ((Instant) randomGenerator.generate(Instant.class)).atZone(UTC).toLocalDate();
    }
}
