package io.javarig.generator;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.Date;

public class DateGenerator implements Generator {
    @Override
    public Date generate() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        return Date.from(randomGenerator.generate(Instant.class));
    }
}
