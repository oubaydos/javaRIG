package io.javarig.generator;

import java.lang.reflect.Type;

/**
 * type generator for classes that hold a type/parameters
 * that cannot be extracted from the class itself (enums, collections...)
 * example: List.class does not give the type of its generic parameter
 */
public interface TypeBasedGenerator extends TypeGenerator {
    Type getType();
    void setType(Type type);
}
