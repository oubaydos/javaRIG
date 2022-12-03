package io.javarig;

import io.javarig.exception.*;
import io.javarig.testclasses.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.*;


@Slf4j
public class RandomInstanceGeneratorTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
    }

    @Test
    public void shouldGenerateString() {
        Object generated = randomInstanceGenerator.generate(String.class);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
    }

    @Test
    public void shouldGenerateStringWithExactSize() {
        int size = 20;
        Object generated = randomInstanceGenerator.generate(String.class, size);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSize(size);
    }

    @Test
    public void shouldGenerateStringWithSizeBetween() {
        int minSize = 20;
        int maxSize = 40;
        Object generated = randomInstanceGenerator.generate(String.class, minSize, maxSize);
        log.info("shouldGenerateString : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(String.class);
        assertThat(generated).asString().hasSizeBetween(minSize, maxSize);
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

    @Test
    public void shouldReturnList() {
        //given
        Class<?> type = String.class;
        //when
        Object generated = randomInstanceGenerator.generate(List.class, type);
        //then
        log.info("shouldReturnList : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(List.class);

        //asserting type
        assertThat(generated).asInstanceOf(LIST).first().isInstanceOf(type);
    }

    @Test
    public void shouldReturnListWithExactSize() {
        //given
        int size = 20;
        Class<?> type = String.class;
        //when
        Object generated = randomInstanceGenerator.generate(List.class, size, type);
        //then
        log.info("shouldReturnListWithExactSize : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(List.class);
        assertThat(generated).asInstanceOf(LIST).hasSize(size);

        //asserting type
        assertThat(generated).asInstanceOf(LIST).first().isInstanceOf(type);
    }

    @Test
    public void shouldReturnListWithSizeBetween() {
        //given
        int minSize = 20;
        int maxSize = 40;
        Class<?> type = String.class;
        //when
        Object generated = randomInstanceGenerator.generate(List.class, minSize, maxSize, type);
        //then
        log.info("shouldReturnListWithSizeBetween : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(List.class);
        assertThat(generated).asInstanceOf(LIST).hasSizeBetween(minSize, maxSize);

        //asserting type
        assertThat(generated).asInstanceOf(LIST).first().isInstanceOf(type);
    }

    @ParameterizedTest
    @ValueSource(classes = {Map.class, TreeMap.class, HashMap.class})
    public void shouldReturnMap(Class<?> mapClass) {
        //given
        Class<?> keyType = String.class;
        Class<?> valueType = Integer.class;
        //when
        Object generated = randomInstanceGenerator.generate(mapClass, keyType, valueType);
        //then
        log.info("shouldReturnMap of type {} : {}", mapClass.getName(), generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(mapClass);

        //asserting keys type
        assertThat(generated).asInstanceOf(MAP).extracting((map) -> map.keySet().toArray()[0]).isInstanceOf(keyType);

        //asserting values type
        assertThat(generated).asInstanceOf(MAP).extracting((map) -> map.values().toArray()[0]).isInstanceOf(valueType);

    }

    @ParameterizedTest
    @ValueSource(classes = {Map.class, TreeMap.class, HashMap.class})
    public void shouldReturnMapWithExactSize(Class<?> mapClass) {
        //given
        int size = 20;
        Class<?> keyType = String.class;
        Class<?> valueType = Integer.class;

        //when
        Object generated = randomInstanceGenerator.generate(mapClass, size, keyType, valueType);
        //then
        log.info("shouldReturnMapWithExactSize of type {} : {}", mapClass.getName(), generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(mapClass);
        assertThat(generated).asInstanceOf(MAP).hasSize(size);

        //asserting keys type
        assertThat(generated).asInstanceOf(MAP).extracting((map) -> map.keySet().toArray()[0]).isInstanceOf(keyType);

        //asserting values type
        assertThat(generated).asInstanceOf(MAP).extracting((map) -> map.values().toArray()[0]).isInstanceOf(valueType);

    }

    @ParameterizedTest
    @ValueSource(classes = {Map.class, TreeMap.class, HashMap.class})
    public void shouldReturnMapWithSizeBetween(Class<?> mapClass) {
        //given
        int minSize = 20;
        int maxSize = 40;
        Class<?> keyType = String.class;
        Class<?> valueType = Integer.class;

        //when
        Object generated = randomInstanceGenerator.generate(mapClass, minSize, maxSize, keyType, valueType);
        //then
        log.info("shouldReturnMapWithSizeBetween of type {} : {}", mapClass.getName(), generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(mapClass);
        assertThat(generated).asInstanceOf(MAP).hasSizeBetween(minSize, maxSize);

        //asserting keys type
        assertThat(generated).asInstanceOf(MAP).extracting((map) -> map.keySet().toArray()[0]).isInstanceOf(keyType);

        //asserting values type
        assertThat(generated).asInstanceOf(MAP).extracting((map) -> map.values().toArray()[0]).isInstanceOf(valueType);

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

    @Test
    public void shouldReturnAnObjectInstance() {
        //when
        Object generated = randomInstanceGenerator.generate(TestClass.class);
        //then
        log.info("shouldReturnAnObjectInstance : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(TestClass.class);
    }

    @Test
    public void shouldThrowNoDefaultConstructorExceptionWhenGivenAClassWithNoDefaultConstructor() {
        //given
        Class<?> type = ClassWithNoDefaultConstructor.class;

        //then
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.generate(type);
        }).isInstanceOf(NoAccessibleDefaultConstructorException.class).hasMessage("Class %s does not have default constructor, or it's not accessible".formatted(type.getName()));
    }

    @Test
    public void shouldLetFieldNullWhenGivenAClassWithASetterWithNoFieldAssociatedToIt() {
        //given
        Type type = ClassWithNoFieldAssociatedToSetter.class;
        //when
        ClassWithNoFieldAssociatedToSetter generated = randomInstanceGenerator.generate(type);
        //then
        assertThat(generated).isNotNull().extracting(ClassWithNoFieldAssociatedToSetter::getS).isNull();
        assertThat(generated).extracting(ClassWithNoFieldAssociatedToSetter::getA).isNotNull();
    }

    @Test
    public void shouldThrowNoAccessibleDefaultConstructorExceptionWhenGivenAClassWithAPrivateConstructor() {
        //given
        Type type = ClassWithPrivateDefaultConstructor.class;

        //then
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.generate(type);
        }).isInstanceOf(NoAccessibleDefaultConstructorException.class).hasMessage("Class %s does not have default constructor, or it's not accessible".formatted(type.getTypeName())).hasCauseInstanceOf(NoSuchMethodException.class);
    }

    @Test
    public void shouldThrowAbstractClassInstantiationExceptionWhenGivenAnAbstractClass() {
        //given
        Type type = AbstractClass.class;

        //then
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.generate(type);
        }).isInstanceOf(AbstractClassInstantiationException.class).hasMessage("%s is abstract. Can't instantiate an abstract class".formatted(type.getTypeName())).hasCauseInstanceOf(InstantiationException.class);
    }

    @Test
    public void shouldThrowInvocationSetterExceptionWhenSetterThrowsAnException() {
        //given
        Type type = ClassWithSetterThatThrowsAnException.class;
        String setterName = "setA";

        //then
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.generate(type);
        }).isInstanceOf(InvocationSetterException.class).hasMessage("got an exception while invoking setter %s in class %s".formatted(setterName, type.getTypeName())).hasCauseInstanceOf(InvocationTargetException.class);
    }

    @Test
    public void shouldThrowNestedObjectExceptionWhenGivenSelfContainingClass() {
        assertThatThrownBy(() -> randomInstanceGenerator.generate(SelfContainingNestedClassTest.class)).isInstanceOf(NestedObjectRecursionException.class);
    }

    @Test
    public void shouldReturnObjectForNestedClasses() {
        //when
        Object generated = randomInstanceGenerator.generate(NestedClass.class);
        //then
        log.info("shouldReturnAnObjectInstance : {}", generated);
        assertThat(generated).isNotNull().isInstanceOf(NestedClass.class);
        assertThat(generated).extracting("testClass").isNotNull().isInstanceOf(TestClass.class);

    }

    @Test
    public void shouldThrowInvalidGenericParamsNumberExceptionWhenTryingToGenerateAListWithAZeroGenericParams() {
        int required = 1;
        assertThatThrownBy(() -> randomInstanceGenerator.generate(List.class))
                .isInstanceOf(InvalidGenericParametersNumberException.class)
                .hasMessage("invalid number of generic parameters, required %d and 0 was found".formatted(required));
    }

    @Test
    public void shouldThrowInvalidGenericParamsNumberExceptionWhenTryingToGenerateAListWithAMoreThanOneGenericParams() {
        int required = 1;
        Type[] genericParams = new Type[]{String.class, Integer.class};
        assertThatThrownBy(() -> randomInstanceGenerator.generate(List.class, genericParams))
                .isInstanceOf(InvalidGenericParametersNumberException.class)
                .hasMessage("invalid number of generic parameters, required %d and %d was found".formatted(required, genericParams.length));
    }

    @Test
    public void shouldThrowInvalidGenericParamsNumberExceptionWhenTryingToGenerateAMapWithAZeroGenericParams() {
        int required = 2;
        assertThatThrownBy(() -> randomInstanceGenerator.generate(Map.class))
                .isInstanceOf(InvalidGenericParametersNumberException.class)
                .hasMessage("invalid number of generic parameters, required %d and 0 was found".formatted(required));
    }


    @Test
    public void shouldThrowInvalidGenericParamsNumberExceptionWhenTryingToGenerateAMapWithAMoreThanTwoGenericParams() {
        int required = 2;
        Type[] genericParams = new Type[]{String.class, Integer.class, Character.class};

        assertThatThrownBy(() -> randomInstanceGenerator.generate(Map.class, genericParams))
                .isInstanceOf(InvalidGenericParametersNumberException.class)
                .hasMessage("invalid number of generic parameters, required %d and %d was found".formatted(required, genericParams.length));
    }
    @Test
    public void shouldThrowInstanceGenerationExceptionWhenConstructorThrowsAnException(){
        //given
        Class<?> type = ClassWithDefaultConstructorThrowingException.class;

        //then
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.generate(type);
        }).isInstanceOf(InstanceGenerationException.class)
                .hasCauseInstanceOf(InvocationTargetException.class);

    }

}
