package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Type;

@Setter
@Getter
public class StringGenerator extends TypeGenerator implements CollectionGenerator {
    private int minSizeInclusive = DEFAULT_MIN_SIZE_INCLUSIVE;
    private int maxSizeExclusive = DEFAULT_MAX_SIZE_EXCLUSIVE;

    public StringGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public String generate() {
        return RandomStringUtils.randomAlphanumeric(this.getMinSizeInclusive(), this.getMaxSizeExclusive());
    }
}
