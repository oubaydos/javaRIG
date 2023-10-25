package io.javarig.config;

import org.apache.commons.lang3.Validate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Configuration {

    @Builder.Default
    private int maxSizeExclusive = DefaultConfigValues.DEFAULT_MAX_SIZE_EXCLUSIVE;
    @Builder.Default
    private int minSizeInclusive = DefaultConfigValues.DEFAULT_MIN_SIZE_INCLUSIVE;

    public static Configuration withSize(int size) {
        validateSize(size);
        return Configuration.builder()
                .maxSizeExclusive(size + 1)
                .minSizeInclusive(size)
                .build();
    }

    public static Configuration withSize(int minSizeInclusive, int maxSizeExclusive) {
        validateSize(minSizeInclusive, maxSizeExclusive);
        return Configuration.builder()
                .maxSizeExclusive(maxSizeExclusive + 1)
                .minSizeInclusive(minSizeInclusive)
                .build();
    }

    private static void validateSize(int minSizeInclusive, int maxSizeExclusive) {
        Validate.isTrue(maxSizeExclusive > minSizeInclusive, "Start value must be smaller than end value.");
        Validate.isTrue(minSizeInclusive >= 0, "Both range values must be non-negative.");
    }

    private static void validateSize(int size) {
        Validate.isTrue(size >= 0, "Size must be non-negative.");
    }
}
