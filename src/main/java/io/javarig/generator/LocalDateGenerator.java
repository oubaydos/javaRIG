package io.javarig.generator;

import java.time.Instant;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

public class LocalDateGenerator extends AbstractGenerator {
    @Override
    public LocalDate generate() {
        return ((Instant) getRandomGenerator().generate(Instant.class)).atZone(UTC).toLocalDate();
    }
}
