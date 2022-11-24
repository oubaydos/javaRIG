package io.javarig.testclasses;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@ToString
public class NestedClassTest {
    private String tempString;
    private NestedClassTest nestedClassTest;
}
