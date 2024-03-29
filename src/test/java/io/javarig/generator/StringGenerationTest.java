package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import io.javarig.config.DefaultConfigValues;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.javarig.util.Utils.removeUnsupportedRegexCharacters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class StringGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
    }


    @Test
    public void shouldGenerateString() {
        Object generated = randomInstanceGenerator.generate(String.class);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
    }

    @Test
    public void shouldGenerateStringWithExactSize() {
        int size = 20;
        Object generated = randomInstanceGenerator.withSize(size).generate(String.class);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSize(size);
    }

    @Test
    public void shouldGenerateStringWithSizeBetween() {
        int minSize = 20;
        int maxSize = 40;
        Object generated = randomInstanceGenerator.withSize(minSize, maxSize).generate(String.class);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSizeBetween(minSize, maxSize-1);
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "[a-zA-Z0-9,;:!?]"})
    public void shouldGenerateStringMatchingRegexPatternBellowGivenMinimumLength(String regexPattern) {
        Object generated = randomInstanceGenerator.withRegexPattern(regexPattern).generate(String.class);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSizeLessThan(DefaultConfigValues.DEFAULT_MIN_SIZE_INCLUSIVE);
        assertThat(generated).asString().matches(regexPattern);
    }
    @Test
    public void shouldGenerateStringMatchingRegexPatternOverGivenMaximumLength() {
        String regexPattern = "abcdefghijklmnopqrstuvwxyz";
        Object generated = randomInstanceGenerator.withRegexPattern(regexPattern).generate(String.class);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSizeGreaterThanOrEqualTo(DefaultConfigValues.DEFAULT_MAX_SIZE_EXCLUSIVE);
        assertThat(generated).asString().matches(regexPattern);
    }
    @Test
    public void shouldGenerateStringMatchingRegexPattern() {
        String regexPattern = "[a-z][A-Z]*[0-9]+a[a(b;c][12]";
        Object generated = randomInstanceGenerator.withRegexPattern(regexPattern).generate(String.class);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSizeBetween(DefaultConfigValues.DEFAULT_MIN_SIZE_INCLUSIVE,DefaultConfigValues.DEFAULT_MAX_SIZE_EXCLUSIVE-1);
        assertThat(generated).asString().matches(regexPattern);
    }
    @Test
    public void shouldGenerateStringMatchingRegexPatternWhileIgnoringUnsupportedCharacters() {
        String regexPattern = "[a-z]^[A-Z]*[0-9]+$a[ab;\\c$$$$^^i][12]";
        String  temp = removeUnsupportedRegexCharacters(regexPattern);
        Object generated = randomInstanceGenerator.withRegexPattern(regexPattern).generate(String.class);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSizeBetween(DefaultConfigValues.DEFAULT_MIN_SIZE_INCLUSIVE,DefaultConfigValues.DEFAULT_MAX_SIZE_EXCLUSIVE-1);
        assertThat(generated).asString().matches(temp);
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionGivenStringHavingMinSizeGreaterThanMaxSize() {
        int minSize = 40;
        int maxSize = 20;
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.withSize(minSize, maxSize).generate(String.class);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start value must be smaller than end value.");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionGivenStringHavingMinSizeSameASMaxSize() {
        int size = 30;
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.withSize(size, size).generate(String.class);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start value must be smaller than end value.");
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenNegativeSize() {
        int size = -20;
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.withSize(size).generate(String.class);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Size must be non-negative.");
    }

}
