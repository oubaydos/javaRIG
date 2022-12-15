package io.javarig.generation.javapredefinedtypes;

import io.javarig.RandomInstanceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class WrappersGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
    }

    @Test
    public void shouldGenerateInteger() {
        Object generated = randomInstanceGenerator.generate(Integer.class);
        log.info("shouldGenerateInteger : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Integer.class);
    }

    @Test
    public void shouldReturnBoolean() {
        Object generated = randomInstanceGenerator.generate(Boolean.class);
        log.info("shouldReturnBoolean : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Boolean.class);
    }

    @Test
    public void shouldReturnFloat() {
        Object generated = randomInstanceGenerator.generate(Float.class);
        log.info("shouldReturnFloat : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Float.class);
    }

    @Test
    public void shouldReturnLong() {
        Object generated = randomInstanceGenerator.generate(Long.class);
        log.info("shouldReturnLong : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Long.class);
    }

    @Test
    public void shouldReturnShort() {
        Object generated = randomInstanceGenerator.generate(Short.class);
        log.info("shouldReturnShort : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Short.class);
    }

    @Test
    public void shouldReturnChar() {
        Object generated = randomInstanceGenerator.generate(Character.class);
        log.info("shouldReturnChar : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Character.class);
    }

    @Test
    public void shouldReturnDouble() {
        Object generated = randomInstanceGenerator.generate(Double.class);
        log.info("shouldReturnDouble : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Double.class);
    }

    @Test
    public void shouldReturnByte() {
        Object generated = randomInstanceGenerator.generate(Byte.class);
        log.info("shouldReturnByte : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Byte.class);
    }
}
