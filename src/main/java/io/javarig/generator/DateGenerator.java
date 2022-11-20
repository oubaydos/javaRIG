package io.javarig.generator;

import java.time.Instant;
import java.util.Date;

public class DateGenerator extends AbstractGenerator {
    @Override
    public Date generate() {
        return Date.from(getRandomGenerator().generate(Instant.class));
    }
}
