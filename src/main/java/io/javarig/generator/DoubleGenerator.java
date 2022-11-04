package io.javarig.generator;

import java.lang.reflect.Type;

public class DoubleGenerator implements Generator{
    @Override
    public Double generate(Type type) {
        return random.nextDouble(0, Double.MAX_VALUE);
    }
}
