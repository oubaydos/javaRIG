package io.javarig.generator;

import java.lang.reflect.Type;

public class IntegerGenerator implements Generator{
    @Override
    public Integer generate(Type ignored) {
        return random.nextInt(0, Integer.MAX_VALUE);
    }
}
