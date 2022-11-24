package io.javarig.generator;

import java.time.Instant;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

public class LocalDateGenerator extends AbstractTypeGenerator {
    @Override
    public LocalDate generate() {
        return ((Instant) getRandomInstanceGenerator().generate(Instant.class)).atZone(UTC).toLocalDate();
    }
}
