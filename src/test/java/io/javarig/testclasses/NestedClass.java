package io.javarig.testclasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NestedClass {
    private String string;
    private Integer a;
    private TestClass testClass;
}
