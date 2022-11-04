package io.javarig.generator;

import java.lang.reflect.Type;

public class LongGenerator implements Generator{
    @Override
    public Long generate(Type type) {
        return random.nextLong(0, Long.MAX_VALUE);
    }
}
