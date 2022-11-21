package io.javarig.generator;

import io.javarig.RandomGenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public interface Generator {
    Random random = new Random();
    RandomGenerator randomGenerator = RandomGenerator.getInstance();
    Object generate() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException;
}
