package io.javarig.exception;

public class NewInstanceCreationException extends InstanceGenerationException {

    public NewInstanceCreationException(Class<?> implementationClass, Throwable cause) {
        super("""
                Error invoking the default constructor for this implementation class %s,
                please make sure that the default constructor exists and it's accessible.
                """.formatted(implementationClass), cause);
    }
}
