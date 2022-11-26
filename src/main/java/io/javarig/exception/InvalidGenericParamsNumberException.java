package io.javarig.exception;

public class InvalidGenericParamsNumberException extends InstanceGenerationException {
    public InvalidGenericParamsNumberException(int required, int found) {
        super("invalid number of generic parameters, required %d and %d was found".formatted(required, found));
    }
}
