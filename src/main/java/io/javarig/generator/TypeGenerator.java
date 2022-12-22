package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import io.javarig.exception.InstanceGenerationException;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.Random;

/**
 * Abstract class responsible for generating random objects for all types
 */
@Setter
@Getter
public abstract class TypeGenerator {
    private final Random random = new Random();
    private final Type type;
    private final RandomInstanceGenerator randomInstanceGenerator;

    public TypeGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        this.type = type;
        this.randomInstanceGenerator = randomInstanceGenerator;
    }

    /**
     * generates a random object, its type is known from the extending class
     */
    public abstract Object generate() throws InstanceGenerationException;
}
