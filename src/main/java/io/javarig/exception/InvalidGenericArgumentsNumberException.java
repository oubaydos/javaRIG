package io.javarig.exception;

public class InvalidGenericArgumentsNumberException extends InstanceGenerationException {
    public InvalidGenericArgumentsNumberException(int required, int found) {
        super("invalid number of generic arguments, required %d and %d was found".formatted(required, found));
    }
}
