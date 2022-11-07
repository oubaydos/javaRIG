package io.javarig.generator;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Setter
@Getter
public class StringGenerator implements CollectionGenerator {
    private int minSize = 5;
    private int maxSize = 15;
    @Override
    public String generate() {
        return RandomStringUtils.randomAlphanumeric(this.getMinSize(), this.getMaxSize());
    }
}