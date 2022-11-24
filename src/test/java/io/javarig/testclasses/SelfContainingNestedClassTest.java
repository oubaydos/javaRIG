package io.javarig.testclasses;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@ToString
public class SelfContainingNestedClassTest {
    private String tempString;
    private SelfContainingNestedClassTest selfContainingNestedClassTest;
}
