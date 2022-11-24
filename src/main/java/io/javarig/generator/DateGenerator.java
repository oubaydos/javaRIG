package io.javarig.generator;

import java.time.Instant;
import java.util.Date;

public class DateGenerator extends AbstractTypeGenerator {
    @Override
    public Date generate() {
        return Date.from(getRandomInstanceGenerator().generate(Instant.class));
    }
}
