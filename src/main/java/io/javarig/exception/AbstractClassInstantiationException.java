package io.javarig.exception;

public class AbstractClassInstantiationException extends InstanceGenerationException {
    public AbstractClassInstantiationException(String className, Throwable cause) {
        super("%s is abstract. Can't instantiate an abstract class".formatted(className), cause);
    }
}
