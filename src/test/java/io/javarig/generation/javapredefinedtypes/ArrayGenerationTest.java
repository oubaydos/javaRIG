package io.javarig.generation.javapredefinedtypes;

import io.javarig.RandomInstanceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.ARRAY;

@Slf4j
public class ArrayGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
    }
    @Test
    public void shouldReturnArray() {
        Object generated = randomInstanceGenerator.generate(Integer[].class);
        log.info("shouldReturnArray : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Integer[].class);
    }

    @Test
    public void shouldReturnArrayWithExactSize() {
        int size = 20;
        Object generated = randomInstanceGenerator.generate(Double[].class,size);
        log.info("shouldReturnArrayWithExactSize : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Double[].class);
        assertThat(generated).asInstanceOf(ARRAY).hasSize(size);
    }

    @Test
    public void shouldReturnArrayWithSizeBetween() {
        int minSize = 20;
        int maxSize = 40;
        Object generated = randomInstanceGenerator.generate(String[].class,minSize, maxSize);
        log.info("shouldReturnArrayWithSizeBetween : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String[].class);
        assertThat(generated).asInstanceOf(ARRAY).hasSizeBetween(minSize, maxSize);
    }

}
