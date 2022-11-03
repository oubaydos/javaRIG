package io.javarig.generator;

import java.lang.reflect.Type;

public class ShortGenerator implements Generator {
    @Override
    public Object generate(Type type) {
        return (short) random.nextInt(0, Short.MAX_VALUE + 1);
    }
}
