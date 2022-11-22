package io.javarig.exception;

public class NoFieldAssociatedToSetter extends InstanceGenerationException {
    public NoFieldAssociatedToSetter(String fieldName, String setterName,Throwable cause) {
        super("No field with name %s associated to setter with name %s".formatted(fieldName, setterName),cause);
    }
}
