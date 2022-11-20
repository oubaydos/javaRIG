package io.javarig;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.TreeSet;

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
    public static void main(String[] args) {

    }
    //    private TestClass2 testClass2;
    private Map<String, String> m;
    //    private List<String> s;
    //    private TestEnum e;
}

