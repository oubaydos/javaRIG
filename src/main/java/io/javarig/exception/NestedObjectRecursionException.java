package io.javarig.exception;

import java.lang.reflect.Type;

public class NestedObjectRecursionException extends RuntimeException {
    public NestedObjectRecursionException(Type type) {
        super(String.format("object %s is composed in a child object, this object can't be generated", type));
    }
}
