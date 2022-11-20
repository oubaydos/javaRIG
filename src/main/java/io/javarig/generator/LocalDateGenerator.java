package io.javarig.generator;

import java.time.Instant;

import static java.time.ZoneOffset.UTC;

public class LocalDateGenerator extends AbstractGenerator {
    @Override
    public Object generate() {
        return ((Instant) getRandomGenerator().generate(Instant.class)).atZone(UTC).toLocalDate();
    }
}
