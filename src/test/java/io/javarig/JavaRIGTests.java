package io.javarig;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.*;


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
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
    }

    @Test
    public void shouldGenerateStringWithExactSize() {
        int size = 20;
        Object generated = randomGenerator.generate(String.class, size);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSize(size);
    }

    @Test
    public void shouldGenerateStringWithSizeBetween() {
        int minSize = 20;
        int maxSize = 40;
        Object generated = randomGenerator.generate(String.class, minSize, maxSize);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSizeBetween(minSize, maxSize);
    }

    @Test
    public void shouldGenerateInteger() {
        Object generated = randomGenerator.generate(Integer.class);
        log.info("shouldGenerateInteger : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Integer.class);
    }

    @Test
    public void shouldReturnBoolean() {
        Object generated = randomGenerator.generate(Boolean.class);
        log.info("shouldReturnBoolean : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Boolean.class);
    }

    @Test
    public void shouldReturnFloat() {
        Object generated = randomGenerator.generate(Float.class);
        log.info("shouldReturnFloat : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Float.class);
    }

    @Test
    public void shouldReturnLong() {
        Object generated = randomGenerator.generate(Long.class);
        log.info("shouldReturnLong : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Long.class);
    }

    @Test
    public void shouldReturnShort() {
        Object generated = randomGenerator.generate(Short.class);
        log.info("shouldReturnShort : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Short.class);
    }

    @Test
    public void shouldReturnChar() {
        Object generated = randomGenerator.generate(Character.class);
        log.info("shouldReturnChar : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Character.class);
    }

    @Test
    public void shouldReturnDouble() {
        Object generated = randomGenerator.generate(Double.class);
        log.info("shouldReturnDouble : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Double.class);
    }

    @Test
    public void shouldReturnByte() {
        Object generated = randomGenerator.generate(Byte.class);
        log.info("shouldReturnByte : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Byte.class);
    }

    @Test
    public void shouldReturnList() {
        //given
        Class<?> type = String.class;
        //when
        Object generated = randomGenerator.generate(List.class, type);
        //then
        log.info("shouldReturnList : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(List.class);

        //asserting type
        assertThat(generated)
                .asInstanceOf(LIST)
                .first()
                .isInstanceOf(type);
    }

    @Test
    public void shouldReturnListWithExactSize() {
        //given
        int size = 20;
        Class<?> type = String.class;
        //when
        Object generated = randomGenerator.generate(List.class, size, type);
        //then
        log.info("shouldReturnListWithExactSize : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(List.class);
        assertThat(generated).asInstanceOf(LIST).hasSize(size);

        //asserting type
        assertThat(generated)
                .asInstanceOf(LIST)
                .first()
                .isInstanceOf(type);
    }

    @Test
    public void shouldReturnListWithSizeBetween() {
        //given
        int minSize = 20;
        int maxSize = 40;
        Class<?> type = String.class;
        //when
        Object generated = randomGenerator.generate(List.class, minSize, maxSize, type);
        //then
        log.info("shouldReturnListWithSizeBetween : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(List.class);
        assertThat(generated).asInstanceOf(LIST).hasSizeBetween(minSize, maxSize);

        //asserting type
        assertThat(generated)
                .asInstanceOf(LIST)
                .first()
                .isInstanceOf(type);
    }

    @Test
    public void shouldReturnMap() {
        //given
        Class<?> keyType = String.class;
        Class<?> valueType = Integer.class;
        //when
        Object generated = randomGenerator.generate(Map.class, keyType, valueType);
        //then
        log.info("shouldReturnMap : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Map.class);

        //asserting keys type
        assertThat(generated)
                .asInstanceOf(MAP)
                .extracting((map) -> map.keySet().toArray()[0])
                .isInstanceOf(keyType);

        //asserting values type
        assertThat(generated)
                .asInstanceOf(MAP)
                .extracting((map) -> map.values().toArray()[0])
                .isInstanceOf(valueType);
    }

    @Test
    public void shouldReturnMapWithExactSize() {
        //given
        int size = 20;
        Class<?> keyType = String.class;
        Class<?> valueType = Integer.class;
        //when
        Object generated = randomGenerator.generate(Map.class, size, keyType, valueType);
        //then
        log.info("shouldReturnMapWithExactSize : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Map.class);
        assertThat(generated).asInstanceOf(MAP).hasSize(size);

        //asserting keys type
        assertThat(generated)
                .asInstanceOf(MAP)
                .extracting((map) -> map.keySet().toArray()[0])
                .isInstanceOf(keyType);

        //asserting values type
        assertThat(generated)
                .asInstanceOf(MAP)
                .extracting((map) -> map.values().toArray()[0])
                .isInstanceOf(valueType);
    }

    @Test
    public void shouldReturnMapWithSizeBetween() {
        //given
        int minSize = 20;
        int maxSize = 40;
        Class<?> keyType = String.class;
        Class<?> valueType = Integer.class;
        //when
        Object generated = randomGenerator.generate(Map.class, minSize, maxSize, keyType, valueType);
        //then
        log.info("shouldReturnMapWithSizeBetween : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Map.class);
        assertThat(generated).asInstanceOf(MAP).hasSizeBetween(minSize, maxSize);

        //asserting keys type
        assertThat(generated)
                .asInstanceOf(MAP)
                .extracting((map) -> map.keySet().toArray()[0])
                .isInstanceOf(keyType);

        //asserting values type
        assertThat(generated)
                .asInstanceOf(MAP)
                .extracting((map) -> map.values().toArray()[0])
                .isInstanceOf(valueType);
    }

    @Test
    public void shouldReturnInstant() {
        Object generated = randomGenerator.generate(Instant.class);
        log.info("shouldReturnInstant : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Instant.class);
    }

    @Test
    public void shouldReturnDate() {
        Object generated = randomGenerator.generate(Date.class);
        log.info("shouldReturnDate : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(Date.class);
    }

    @Test
    public void shouldReturnLocalDate() {
        Object generated = randomGenerator.generate(LocalDate.class);
        log.info("shouldReturnLocalDate : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(LocalDate.class);
    }

    @Test
    public void shouldReturnByteArray() {
        Object generated = randomGenerator.generate(byte[].class);
        log.info("shouldReturnByteArray : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(byte[].class);
    }

    @Test
    public void shouldReturnByteArrayWithExactSize() {
        int size = 20;
        Object generated = randomGenerator.generate(byte[].class, size);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(byte[].class);
        assertThat(generated).asInstanceOf(BYTE_ARRAY).hasSize(size);
    }

    @Test
    public void shouldReturnByteArrayWithSizeBetween() {
        int minSize = 20;
        int maxSize = 40;
        Object generated = randomGenerator.generate(byte[].class, minSize, maxSize);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(byte[].class);
        assertThat(generated).asInstanceOf(BYTE_ARRAY).hasSizeBetween(minSize, maxSize);
    }
    @Test
    public void shouldReturnEnum(){
        //when
        Object generated = randomGenerator.generate(TestEnum.class);
        //then
        log.info("shouldReturnEnum : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(TestEnum.class);
    }
    @Test
    public void shouldReturnNullEnum(){
        //when
        Object generated = randomGenerator.generate(NullTestEnum.class);
        //then
        log.info("shouldReturnEnum : {}", generated);
        assertThat(generated).isNull();
    }

    @Test
    public void shouldReturnAnObjectInstance(){
        //when
        Object generated = randomGenerator.generate(TestClass.class);
        //then
        log.info("shouldReturnAnObjectInstance : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(TestClass.class);
    }
}