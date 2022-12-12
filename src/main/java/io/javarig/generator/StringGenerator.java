package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Type;

@Setter
@Getter
public class StringGenerator extends AbstractTypeGenerator implements CollectionGenerator {
    private int minSizeInclusive = 5;
    private int maxSizeExclusive = 15;

    public StringGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public String generate() {
        return RandomStringUtils.randomAlphanumeric(this.getMinSizeInclusive(), this.getMaxSizeExclusive());
    }
}
