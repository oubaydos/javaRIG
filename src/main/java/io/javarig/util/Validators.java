package io.javarig.util;

import org.apache.commons.lang3.Validate;

import com.mifmif.common.regex.Generex;

public class Validators {

    public static void validateSize(int minSizeInclusive, int maxSizeExclusive) {
        Validate.isTrue(maxSizeExclusive > minSizeInclusive, "Start value must be smaller than end value.");
        Validate.isTrue(minSizeInclusive >= 0, "Both range values must be non-negative.");
    }

    public static void validateSize(int size) {
        Validate.isTrue(size >= 0, "Size must be non-negative.");
    }

    public static void validateRegexPattern(String regexPattern) {
        Validate.isTrue(Generex.isValidPattern(regexPattern), "regex pattern not valid (or not supported).");
    }
}
