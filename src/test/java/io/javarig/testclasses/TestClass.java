package io.javarig.testclasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

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
@Slf4j
public class TestClass {
    private boolean b;
    private short s;
    private int i;
    private Map<String, String> m;
    private List<String> l;
}

