package io.javarig.generator;

import io.javarig.exception.InstanceGenerationException;

/**
 * Interface responsible for generating random objects for all types
 */
public interface TypeGenerator {
    /**
     * generates a random object, its type is known from the extending class
     */
    Object generate() throws InstanceGenerationException;
}
