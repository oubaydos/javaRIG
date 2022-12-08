package io.javarig.generator;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Setter
@Getter
public class StringGenerator extends AbstractTypeGenerator implements CollectionGenerator {
    private int minSizeInclusive = DEFAULT_MIN_SIZE_INCLUSIVE;
    private int maxSizeExclusive = DEFAULT_MAX_SIZE_INCLUSIVE;

    @Override
    public String generate() {
        return RandomStringUtils.randomAlphanumeric(this.getMinSizeInclusive(), this.getMaxSizeExclusive());
    }
}
