package io.javarig.exception;

public class NoAccessibleDefaultConstructorException extends InstanceGenerationException{
    public NoAccessibleDefaultConstructorException(Class<?> type, Throwable cause) {
        super("Class %s does not have default constructor, or it's not accessible".formatted(type.getName()),cause);
    }

}
