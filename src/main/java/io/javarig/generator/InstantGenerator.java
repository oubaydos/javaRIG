package io.javarig.generator;

import java.time.Instant;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

public class InstantGenerator implements Generator {

    private final Instant MIN_INSTANT = Instant.ofEpochMilli(0);
    private final Instant MAX_INSTANT = LocalDate.of(2100, 12, 31).atStartOfDay(UTC).toInstant();

    @Override
    public Instant generate() {
        return Instant.ofEpochMilli(random.nextLong(MIN_INSTANT.toEpochMilli(), MAX_INSTANT.toEpochMilli()));
    }
}
