package io.javarig.exception;

public class NoDefaultConstructorException extends InstanceGenerationException{
    public NoDefaultConstructorException(Class<?> type,Throwable cause) {
        super("Class %s does not have default constructor, please create a default constructor for it".formatted(type.getName()),cause);
    }

}
