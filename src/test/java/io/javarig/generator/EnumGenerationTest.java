package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import io.javarig.testclasses.NullTestEnum;
import io.javarig.testclasses.TestEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class EnumGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
    }
    @Test
    public void shouldReturnEnum() {
        //when
        Object generated = randomInstanceGenerator.generate(TestEnum.class);
        //then
        log.info("shouldReturnEnum : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(TestEnum.class);
    }

    @Test
    public void shouldReturnNullEnum() {
        //when
        Object generated = randomInstanceGenerator.generate(NullTestEnum.class);
        //then
        log.info("shouldReturnEnum : {}", generated);
        assertThat(generated).isNull();
    }

}
