package io.javarig.generation.javapredefinedtypes;

import io.javarig.RandomInstanceGenerator;
import io.javarig.testclasses.TestClass;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.ARRAY;
import static org.assertj.core.api.InstanceOfAssertFactories.DOUBLE_ARRAY;

@Slf4j
public class ArrayGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
    }

    @ParameterizedTest
    @ValueSource(classes = {Integer[].class, int[].class})
    public void shouldReturnIntegerArray(Class<?> testClass) {
        Object generated = randomInstanceGenerator.generate(testClass);
        log.info("shouldReturnArray : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(testClass);
    }

    @Test
    public void shouldReturnWrapperArrayWithExactSize() {
        int size = 20;
        Object generated = randomInstanceGenerator.generate(Double[].class, size);
        log.info("shouldReturnArrayWithExactSize : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Double[].class);
        assertThat(generated).asInstanceOf(ARRAY).hasSize(size);
    }

    @Test
    public void shouldReturnPrimitiveArrayWithExactSize() {
        int size = 20;
        Object generated = randomInstanceGenerator.generate(double[].class, size);
        log.info("shouldReturnArrayWithExactSize : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(double[].class);
        assertThat(generated).asInstanceOf(DOUBLE_ARRAY).hasSize(size);
    }

    @Test
    public void shouldReturnArrayWithSizeBetween() {
        int minSize = 20;
        int maxSize = 40;
        Object generated = randomInstanceGenerator.generate(String[].class, minSize, maxSize);
        log.info("shouldReturnArrayWithSizeBetween : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String[].class);
        assertThat(generated).asInstanceOf(ARRAY).hasSizeBetween(minSize, maxSize);
    }

    @Test
    public void shouldReturn2DArray() {
        Object generated = randomInstanceGenerator.generate(double[][].class);
        log.info("shouldReturn2DArray : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(double[][].class);
    }

    @Test
    public void shouldReturnTestClassArrayWithExactSize() {
        int size = 22;
        Object generated = randomInstanceGenerator.generate(TestClass[].class, size);
        log.info("shouldReturnTestClassArrayWithExactSize {} : {}", size, generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(TestClass[].class);
    }

}
