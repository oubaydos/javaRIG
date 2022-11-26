package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * type generator that offers a Random instance for its children
 *
 * @see java.util.Random
 */
@Setter
@Getter
public abstract class AbstractTypeGenerator implements TypeGenerator {
    private Random random = new Random();
    private RandomInstanceGenerator randomInstanceGenerator = new RandomInstanceGenerator();
}
