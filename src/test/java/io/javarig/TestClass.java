package io.javarig;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * this is the test class for which we will be trying to generate random objects
 * it is annotated with @Setter
 *
 * @author obaydah bouifadene
 * @see lombok.Setter
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TestClass {
    //    private TestClass2 testClass2;
    private Object m;
//    private List<String> s;
//    private TestEnum e;
}

enum TestEnum {
    A, B, C
}