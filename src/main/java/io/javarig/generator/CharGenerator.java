package io.javarig.generator;

import org.apache.commons.lang3.RandomStringUtils;

public class CharGenerator implements Generator {
    @Override
    public Character generate() {
        return RandomStringUtils.randomAlphabetic(1).charAt(0);
    }
}
