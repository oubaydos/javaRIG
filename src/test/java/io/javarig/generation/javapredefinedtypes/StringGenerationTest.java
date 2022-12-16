package io.javarig.generation.javapredefinedtypes;

import io.javarig.RandomInstanceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Object generated = randomInstanceGenerator.generate(String.class, size);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSize(size);
    }

    @Test
    public void shouldGenerateStringWithSizeBetween() {
        int minSize = 20;
        int maxSize = 40;
        Object generated = randomInstanceGenerator.generate(String.class, minSize, maxSize);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSizeBetween(minSize, maxSize);
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionGivenStringHavingMinSizeGreaterThanMaxSize() {
        int minSize = 40;
        int maxSize = 20;
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.generate(String.class, minSize, maxSize);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start value must be smaller than end value.");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionGivenStringHavingMinSizeSameASMaxSize() {
        int size = 30;
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.generate(String.class, size, size);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start value must be smaller than end value.");
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenNegativeSize() {
        int size = -20;
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.generate(String.class, size);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Size must be non-negative.");
    }
}
