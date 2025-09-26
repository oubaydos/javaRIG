package io.javarig.generator.time;

import io.javarig.RandomInstanceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.*;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class DateTimeGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
    }

    static List<Class<?>> provideDateClasses() {
        return List.of(Instant.class, Date.class, LocalDate.class, LocalDateTime.class, OffsetDateTime.class, ZonedDateTime.class, LocalTime.class, OffsetDateTime.class);
    }

    @ParameterizedTest
    @MethodSource("provideDateClasses")
    public void shouldReturnMultipleDateTypes(Class<?> clazz) {
        Object generated = randomInstanceGenerator.generate(clazz);
        log.info("should return {} : {}", clazz.getName(), generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(clazz);
    }
}
