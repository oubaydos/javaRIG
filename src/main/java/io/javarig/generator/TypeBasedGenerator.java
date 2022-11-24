package io.javarig.generator;

import java.lang.reflect.Type;

public interface TypeBasedGenerator extends TypeGenerator {
    Type getType();

    void setType(Type type);
}
