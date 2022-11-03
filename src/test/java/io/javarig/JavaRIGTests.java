package io.javarig;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;



@Slf4j
public class JavaRIGTests {
    private RandomGenerator randomGenerator;
    @BeforeEach
    public void setUp() {
        randomGenerator = new RandomGenerator();
    }

    @Test
    public void shouldGenerateString() {
        Object generated = randomGenerator.generate(String.class);
        log.info("shouldGenerateString : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
    }

    @Test
    public void shouldGenerateStringWithExactSize() {
        int size = 20;
        Object generated = randomGenerator.generate(String.class,size);
        log.info("shouldGenerateString : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSize(size);
    }

    @Test
    public void shouldGenerateStringBetweenMinAndMaxSize() {
        int minSize = 20;
        int maxSize = 40;
        Object generated = randomGenerator.generate(String.class,minSize,maxSize);
        log.info("shouldGenerateString : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSizeBetween(minSize,maxSize);
    }

    @Test
    public void shouldGenerateInteger(){
        Object generated = randomGenerator.generate(Integer.class);
        log.info("shouldGenerateInteger : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Integer.class);
    }

    @Test
    public void shouldReturnBoolean(){
        Object generated = randomGenerator.generate(Boolean.class);
        log.info("shouldReturnBoolean : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Boolean.class);
    }

    @Test
    public void shouldReturnFloat(){
        Object generated = randomGenerator.generate(Float.class);
        log.info("shouldReturnFloat : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Float.class);
    }

    @Test
    public void shouldReturnLong(){
        Object generated = randomGenerator.generate(Long.class);
        log.info("shouldReturnLong : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Long.class);
    }

    @Test
    public void shouldReturnShort(){
        Object generated = randomGenerator.generate(Short.class);
        log.info("shouldReturnShort : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Short.class);
    }

    @Test
    public void shouldReturnChar(){
        Object generated = randomGenerator.generate(Character.class);
        log.info("shouldReturnChar : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Character.class);
    }

    @Test
    public void shouldReturnDouble(){
        Object generated = randomGenerator.generate(Double.class);
        log.info("shouldReturnDouble : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Double.class);
    }

    @Test
    public void shouldReturnByte(){
        Object generated = randomGenerator.generate(Byte.class);
        log.info("shouldReturnByte : {}",generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Byte.class);
    }
}