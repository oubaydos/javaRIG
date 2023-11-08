package io.javarig.config;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
@With
public class Configuration {

    @Builder.Default
    private int maxSizeExclusive = DefaultConfigValues.DEFAULT_MAX_SIZE_EXCLUSIVE;
    @Builder.Default
    private int minSizeInclusive = DefaultConfigValues.DEFAULT_MIN_SIZE_INCLUSIVE;
    @Builder.Default
    private String regexPattern = DefaultConfigValues.DEFAULT_REGEX_PATTERN;
    // todo should override the build method to validate before building

}
