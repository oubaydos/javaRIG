package io.javarig.generator;

import java.lang.reflect.Type;

/**
 * type generator for classes that require an extra type
 * other than the class itself (enums, collections...)
 */
public interface TypeBasedGenerator extends TypeGenerator {

    Type getType();
    void setType(Type type);
}
