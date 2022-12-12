package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.Random;

/**
 * type generator that offers a Random instance for its children
 *
 * @see java.util.Random
 */
@Setter
@Getter
public abstract class AbstractTypeGenerator implements TypeGenerator {
    private final Random random = new Random();
    private final Type type;
    private final RandomInstanceGenerator randomInstanceGenerator;

    public AbstractTypeGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        this.type = type;
        this.randomInstanceGenerator = randomInstanceGenerator;
    }
}
