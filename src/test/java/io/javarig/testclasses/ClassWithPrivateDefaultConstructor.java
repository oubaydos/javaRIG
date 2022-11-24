package io.javarig.testclasses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClassWithPrivateDefaultConstructor {
    int a;

    private ClassWithPrivateDefaultConstructor() {
    }
}
