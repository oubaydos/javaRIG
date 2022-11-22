package io.javarig.exception;

public class InstanceGenerationException extends RuntimeException{
    public InstanceGenerationException(Throwable cause) {
        super(cause);
    }

    public InstanceGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
