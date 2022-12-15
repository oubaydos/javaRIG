package io.javarig.exception;

public class JavaRIGInternalException extends InstanceGenerationException {

    public JavaRIGInternalException(Throwable cause) {
        super("""
                JavaRIG Internal Error : if you get this error,
                please create an issue in the github repository
                """, cause);
    }
}
