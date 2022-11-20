package io.javarig.generator;

import java.lang.reflect.Type;

public interface TypeBasedGenerator extends Generator {
    Type getType();

    void setType(Type type);
}
