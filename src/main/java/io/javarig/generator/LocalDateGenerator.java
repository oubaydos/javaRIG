package io.javarig.generator;

import java.time.Instant;

import static java.time.ZoneOffset.UTC;

public class LocalDateGenerator implements Generator {
    @Override
    public Object generate() {
        return ((Instant) randomGenerator.generate(Instant.class)).atZone(UTC).toLocalDate();
    }
}
