package io.javarig.exception;

@Deprecated
public class NoFieldAssociatedToSetter extends InstanceGenerationException {
    /**
     * exception to be thrown when a field has no setter ( i.e. method setX(X))
     * @param fieldName the field without the setter
     * @param setterName expected setter method
     * @param cause base exception
     * @deprecated we will no longer throw this exception, instead we will just ignore fields without setters
     */
    public NoFieldAssociatedToSetter(String fieldName, String setterName,Throwable cause) {
        super("No field with name %s associated to setter with name %s".formatted(fieldName, setterName),cause);
    }
}
