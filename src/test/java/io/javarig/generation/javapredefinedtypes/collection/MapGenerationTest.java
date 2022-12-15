package io.javarig.generation.javapredefinedtypes.collection;

import io.javarig.ParameterizedTypeImpl;
import io.javarig.RandomInstanceGenerator;
import io.javarig.exception.InvalidGenericParametersNumberException;
import io.javarig.exception.JavaRIGInternalException;
import io.javarig.generator.collection.map.MapGenerator;
import io.javarig.testclasses.FakeMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;

@Slf4j
public class MapGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
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
    @SuppressWarnings("rawtypes")
    @Test
    public void shouldThrowNewInstanceCreationExceptionIfAMapGeneratorImplementationDoesNotHaveDefaultConstructor() {
        final Class<? extends Map> fakeMapClass = FakeMap.class;
        ParameterizedTypeImpl typeToGenerate = new ParameterizedTypeImpl(new Type[]{String.class, String.class}, fakeMapClass);
        MapGenerator fakeMapGenerator = new MapGenerator(typeToGenerate, new RandomInstanceGenerator()) {
            @Override
            public Class<? extends Map> getImplementationType() {
                return fakeMapClass;
            }
        };

        assertThatThrownBy(fakeMapGenerator::generate)
                .isInstanceOf(JavaRIGInternalException.class)
                .hasMessage("""
                        JavaRIG Internal Error : if you get this error,
                        please create an issue in the github repository
                        """);
    }

}
