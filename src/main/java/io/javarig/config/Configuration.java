package io.javarig.config;

import com.mifmif.common.regex.Generex;
import org.apache.commons.lang3.Validate;

import lombok.Builder;
import lombok.Getter;

import static io.javarig.util.Utils.removeUnsupportedRegexCharacters;

@Getter
@Builder
public class Configuration {

    @Builder.Default
    private int maxSizeExclusive = DefaultConfigValues.DEFAULT_MAX_SIZE_EXCLUSIVE;
    @Builder.Default
    private int minSizeInclusive = DefaultConfigValues.DEFAULT_MIN_SIZE_INCLUSIVE;
    @Builder.Default
    private String regexPattern = DefaultConfigValues.DEFAULT_REGEX_PATTERN;

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
                .maxSizeExclusive(maxSizeExclusive)
                .minSizeInclusive(minSizeInclusive)
                .build();
    }

    public static Configuration withRegexPattern(String regexPattern) {
        validateRegexPattern(removeUnsupportedRegexCharacters(regexPattern));
        return Configuration.builder()
                .regexPattern(regexPattern)
                .build();
    }

    // validations
    private static void validateSize(int minSizeInclusive, int maxSizeExclusive) {
        Validate.isTrue(maxSizeExclusive > minSizeInclusive, "Start value must be smaller than end value.");
        Validate.isTrue(minSizeInclusive >= 0, "Both range values must be non-negative.");
    }

    private static void validateSize(int size) {
        Validate.isTrue(size >= 0, "Size must be non-negative.");
    }

    private static void validateRegexPattern(String regexPattern) {
        Validate.isTrue(Generex.isValidPattern(regexPattern), "regex pattern not valid (or not supported).");
    }
    // todo should override the build method to validate before building

}
