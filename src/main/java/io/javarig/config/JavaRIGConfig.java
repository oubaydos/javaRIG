package io.javarig.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JavaRIGConfig {
    @Builder.Default private int maxSizeExclusive = DefaultConfigValues.DEFAULT_MAX_SIZE_EXCLUSIVE;
    @Builder.Default private int minSizeInclusive = DefaultConfigValues.DEFAULT_MIN_SIZE_INCLUSIVE;
}
