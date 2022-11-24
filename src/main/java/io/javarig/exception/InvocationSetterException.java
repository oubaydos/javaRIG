package io.javarig.exception;

public class InvocationSetterException extends InstanceGenerationException {
    public InvocationSetterException(String setterName, String className, Throwable cause) {
        super("got an exception while invoking setter %s in class %s".formatted(setterName, className), cause);
    }
}
