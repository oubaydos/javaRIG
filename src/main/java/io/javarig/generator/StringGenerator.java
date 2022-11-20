package io.javarig.generator;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Setter
@Getter
public class StringGenerator extends AbstractGenerator implements CollectionGenerator {
    private int minSizeInclusive = 5;
    private int maxSizeExclusive = 15;

    @Override
    public String generate() {
        return RandomStringUtils.randomAlphanumeric(this.getMinSizeInclusive(), this.getMaxSizeExclusive());
    }
}
