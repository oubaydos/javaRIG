package io.javarig.generator;

import java.time.Instant;
import java.util.Date;

public class DateGenerator implements Generator {
    @Override
    public Date generate() {
        return Date.from(randomGenerator.generate(Instant.class));
    }
}
