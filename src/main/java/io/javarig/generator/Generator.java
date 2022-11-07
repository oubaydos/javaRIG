package io.javarig.generator;

import io.javarig.RandomGenerator;

import java.util.Random;

public interface Generator {
    Random random = new Random();
    RandomGenerator randomGenerator = new RandomGenerator();
    Object generate();
}
