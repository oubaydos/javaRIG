package io.javarig.generation.javapredefinedtypes;

import io.javarig.RandomInstanceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.BYTE_ARRAY;

@Slf4j
public class ArrayGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
    }
    @Test
    public void shouldReturnByteArray() {
        Object generated = randomInstanceGenerator.generate(byte[].class);
        log.info("shouldReturnByteArray : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(byte[].class);
    }

    @Test
    public void shouldReturnByteArrayWithExactSize() {
        int size = 20;
        Object generated = randomInstanceGenerator.generate(byte[].class, size);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(byte[].class);
        assertThat(generated).asInstanceOf(BYTE_ARRAY).hasSize(size);
    }

    @Test
    public void shouldReturnByteArrayWithSizeBetween() {
        int minSize = 20;
        int maxSize = 40;
        Object generated = randomInstanceGenerator.generate(byte[].class, minSize, maxSize);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(byte[].class);
        assertThat(generated).asInstanceOf(BYTE_ARRAY).hasSizeBetween(minSize, maxSize);
    }

}
