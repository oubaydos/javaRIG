package io.javarig.generation.javapredefinedtypes;

import io.javarig.RandomInstanceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class DateTimeGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
    }
    @Test
    public void shouldReturnInstant() {
        Object generated = randomInstanceGenerator.generate(Instant.class);
        log.info("shouldReturnInstant : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Instant.class);
    }

    @Test
    public void shouldReturnDate() {
        Object generated = randomInstanceGenerator.generate(Date.class);
        log.info("shouldReturnDate : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Date.class);
    }

    @Test
    public void shouldReturnLocalDate() {
        Object generated = randomInstanceGenerator.generate(LocalDate.class);
        log.info("shouldReturnLocalDate : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(LocalDate.class);
    }

}
