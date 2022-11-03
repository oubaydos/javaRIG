package io.javarig;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;



@Slf4j
public class JavaRIGTests {
    private RandomGeneratorV2 randomGeneratorV2;
    @BeforeEach
    public void setUp() {
        randomGeneratorV2 = new RandomGeneratorV2();
    }

    @Test
    public void shouldGenerateString() {
        Object generated = randomGeneratorV2.generate(String.class);
        log.info("shouldGenerateString : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
    }

    @Test
    public void shouldGenerateStringWithExactSize() {
        int size = 20;
        Object generated = randomGeneratorV2.generate(String.class,size);
        log.info("shouldGenerateString : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSize(size);
    }

    @Test
    public void shouldGenerateStringBetweenMinAndMaxSize() {
        int minSize = 20;
        int maxSize = 40;
        Object generated = randomGeneratorV2.generate(String.class,minSize,maxSize);
        log.info("shouldGenerateString : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSizeBetween(minSize,maxSize);
    }

    @Test
    public void shouldGenerateInteger(){
        Object generated = randomGeneratorV2.generate(Integer.class);
        log.info("shouldGenerateInteger : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Integer.class);
    }
}