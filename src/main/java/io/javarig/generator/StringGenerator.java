package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Type;

@Setter
@Getter
public class StringGenerator extends TypeGenerator {
    public StringGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public String generate() {
        return RandomStringUtils.randomAlphanumeric(getConfig().getMinSizeInclusive(), getConfig().getMaxSizeExclusive());
    }
}
