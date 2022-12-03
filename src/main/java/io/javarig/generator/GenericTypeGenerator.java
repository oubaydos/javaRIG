package io.javarig.generator;

import io.javarig.exception.InvalidGenericParametersNumberException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface GenericTypeGenerator {
    int getNumberOfGenericParams();

    default void checkIfValidNumberOfGenericArguments(Type type) {
        if (type instanceof ParameterizedType parameterizedType && parameterizedType.getActualTypeArguments().length != getNumberOfGenericParams()) {
            throw new InvalidGenericParametersNumberException(getNumberOfGenericParams(), parameterizedType.getActualTypeArguments().length);

        }
        if (!(type instanceof ParameterizedType)) {
            throw new InvalidGenericParametersNumberException(getNumberOfGenericParams(), 0);
        }
    }
}
