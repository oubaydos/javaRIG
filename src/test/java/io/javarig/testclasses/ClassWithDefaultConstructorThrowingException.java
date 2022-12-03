package io.javarig.testclasses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClassWithDefaultConstructorThrowingException {
    int a;

    public ClassWithDefaultConstructorThrowingException() {
        throw new IllegalStateException("constructor throwing an exception");
    }
}
