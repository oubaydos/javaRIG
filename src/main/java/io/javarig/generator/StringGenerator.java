package io.javarig.generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Type;

public class StringGenerator extends CollectionGenerator{
    @Override
    public String generate(Type type) {
        return RandomStringUtils.randomAlphanumeric(minSize,maxSize);
    }
}
