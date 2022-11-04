package io.javarig.generator;

import java.lang.reflect.Type;

public class FloatGenerator implements Generator {
    @Override
    public Float generate(Type type) {
        return random.nextFloat(0, Float.MAX_VALUE + 1);
    }
}
