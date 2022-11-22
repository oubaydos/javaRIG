package io.javarig.generator;

import io.javarig.exception.InstanceGenerationException;

public interface Generator {
    Object generate() throws InstanceGenerationException;
}
