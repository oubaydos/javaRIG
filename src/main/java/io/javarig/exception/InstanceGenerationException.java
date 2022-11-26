package io.javarig.exception;

/**
 * Base exception for all javaRIG exceptions
 */
public class InstanceGenerationException extends RuntimeException{
    public InstanceGenerationException(Throwable cause) {
        super(cause);
    }

    public InstanceGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstanceGenerationException(String message) {
        super(message);
    }
}
