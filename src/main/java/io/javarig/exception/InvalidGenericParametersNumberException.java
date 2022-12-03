package io.javarig.exception;

public class InvalidGenericParametersNumberException extends InstanceGenerationException {
    public InvalidGenericParametersNumberException(int required, int found) {
        super("invalid number of generic parameters, required %d and %d was found".formatted(required, found));
    }
}
