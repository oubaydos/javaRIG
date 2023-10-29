package io.javarig.generation.javapredefinedtypes.collection;

import io.javarig.ParameterizedTypeImpl;
import io.javarig.RandomInstanceGenerator;
import io.javarig.config.Configuration;
import io.javarig.exception.InvalidGenericParametersNumberException;
import io.javarig.exception.JavaRIGInternalException;
import io.javarig.generator.collection.list.ListGenerator;
import io.javarig.testclasses.FakeList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Type;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.COLLECTION;

@Slf4j
public class SingleGenericTypeCollectionGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;
    private static final Class<?>[] implementedCollectionClasses = {List.class, ArrayList.class, Set.class, HashSet.class, LinkedHashSet.class, TreeSet.class};

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
    }
    private static Class<?>[] getImplementedCollectionClasses(){
        return SingleGenericTypeCollectionGenerationTest.implementedCollectionClasses;
    }

    @ParameterizedTest
    @MethodSource("getImplementedCollectionClasses")
    public void shouldReturnCollectionOfAppropriateType(Class<?> collectionClass) {
        //given
        Class<?> type = String.class;
        //when
        Object generated = randomInstanceGenerator.generate(collectionClass, type);
        //then
        log.info("shouldReturn{} : {}",collectionClass.getSimpleName(), generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(collectionClass);

        //asserting type
        assertThat(generated).asInstanceOf(COLLECTION).first().isInstanceOf(type);
    }

    @ParameterizedTest
    @MethodSource("getImplementedCollectionClasses")
    public void shouldReturnTheAppropriateCollectionWithExactSize(Class<?> collectionClass) {
        //given
        int size = 20;
        Class<?> type = String.class;
        //when
        Object generated = randomInstanceGenerator.generate(collectionClass, Configuration.withSize(size), type);
        //then
        log.info("shouldReturn{}WithExactSize : {}",collectionClass.getSimpleName(), generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(collectionClass);
        assertThat(generated).asInstanceOf(COLLECTION).hasSize(size);

        //asserting type
        assertThat(generated).asInstanceOf(COLLECTION).first().isInstanceOf(type);
    }

    @ParameterizedTest
    @MethodSource("getImplementedCollectionClasses")
    public void shouldReturnTheAppropriateCollectionWithSizeBetween(Class<?> collectionClass) {
        //given
        int minSize = 20;
        int maxSize = 40;
        Class<?> type = String.class;
        //when
        Object generated = randomInstanceGenerator.generate(collectionClass, Configuration.withSize(minSize, maxSize), type);
        //then
        log.info("shouldReturnListWithSizeBetween : {}", generated);
        assertThat(generated).isNotNull();
        assertThat(generated).isInstanceOf(collectionClass);
        assertThat(generated).asInstanceOf(COLLECTION).hasSizeBetween(minSize, maxSize-1);

        //asserting type
        assertThat(generated).asInstanceOf(COLLECTION).first().isInstanceOf(type);
    }
    @ParameterizedTest
    @MethodSource("getImplementedCollectionClasses")
    public void shouldThrowInvalidGenericParamsNumberExceptionWhenTryingToGenerateACollectionWithAZeroGenericParams(Class<?> collectionClass) {
        int required = 1;
        assertThatThrownBy(() -> randomInstanceGenerator.generate(collectionClass))
                .isInstanceOf(InvalidGenericParametersNumberException.class)
                .hasMessage("invalid number of generic parameters, required %d and 0 was found".formatted(required));
    }

    @ParameterizedTest
    @MethodSource("getImplementedCollectionClasses")
    public void shouldThrowInvalidGenericParamsNumberExceptionWhenTryingToGenerateACollectionWithAMoreThanOneGenericParams(Class<?> collectionClass) {
        int required = 1;
        Type[] genericParams = new Type[]{String.class, Integer.class};
        assertThatThrownBy(() -> randomInstanceGenerator.generate(collectionClass, genericParams))
                .isInstanceOf(InvalidGenericParametersNumberException.class)
                .hasMessage("invalid number of generic parameters, required %d and %d was found".formatted(required, genericParams.length)); // FIXME is this how should we check for message ? it would be better if we checked a type rather than a string that could be changed in the future without necessarily breaking the code
    }
    @ParameterizedTest
    @MethodSource("getImplementedCollectionClasses")
    public void shouldThrowIllegalArgumentExceptionGivenMinSizeEqualsMaxSize(Class<?> collectionClass) {
        int size = 30;
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.generate(collectionClass, Configuration.withSize(size, size));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start value must be smaller than end value.");
    }
    @SuppressWarnings("rawtypes")
    @Test
    public void shouldThrowNewInstanceCreationExceptionIfAListGeneratorImplementationDoesNotHaveADefaultConstructor() {
        final Class<? extends List> fakeListClass = FakeList.class;
        ParameterizedTypeImpl typeToGenerate = new ParameterizedTypeImpl(new Type[]{String.class}, fakeListClass);
        ListGenerator fakeListGenerator = new ListGenerator(typeToGenerate, new RandomInstanceGenerator()) {
            @Override
            public Class<? extends List> getImplementationType() {
                return fakeListClass;
            }
        };

        assertThatThrownBy(fakeListGenerator::generate)
                .isInstanceOf(JavaRIGInternalException.class)
                .hasMessage("""
                         JavaRIG Internal Error : if you get this error,
                         please create an issue in the github repository
                         """);
    }

}
