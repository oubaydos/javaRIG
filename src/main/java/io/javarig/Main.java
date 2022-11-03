package io.javarig;

import io.javarig.generator.RandomGenerator;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;


/**
 * this is our playground for testing our code
 */
@Slf4j
public class Main {
    private static final RandomGenerator randomGenerator = new RandomGenerator();

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException, ClassNotFoundException {
        log.info(randomGenerator.generate(TestClass.class).toString());
    }

}
