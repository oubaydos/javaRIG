package io.javarig.generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Type;

public class CharGenerator implements Generator {
    @Override
    public Character generate(Type type) {
        return RandomStringUtils.randomAlphabetic(1).charAt(0);
    }
}
