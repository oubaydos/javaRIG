package io.javarig.generator;

import java.lang.reflect.ParameterizedType;

public interface GenericTypeGenerator extends Generator {
    ParameterizedType getType();

    void setType(ParameterizedType type);
}
