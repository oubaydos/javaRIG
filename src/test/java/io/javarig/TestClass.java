package io.javarig;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Type;
import java.util.*;

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
    //    private TestClass2 testClass2;
    public static void main(String[] args) {
        Map<String,String> test ;

        Type type = new ParameterizedTypeImpl(new Type[]{String.class,String.class},Map.class,null);
        log.info(type.getTypeName());
    }
    private Map<String,String> m;
//    private List<String> s;
//    private TestEnum e;
}

enum TestEnum {
    A, B, C
}