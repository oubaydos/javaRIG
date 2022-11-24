package io.javarig.generator;

import io.javarig.exception.InstanceGenerationException;

public interface TypeGenerator {
    Object generate() throws InstanceGenerationException;
}
