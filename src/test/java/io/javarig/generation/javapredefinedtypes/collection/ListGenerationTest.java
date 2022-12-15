package io.javarig.generation.javapredefinedtypes.collection;

import io.javarig.ParameterizedTypeImpl;
import io.javarig.RandomInstanceGenerator;
import io.javarig.exception.InvalidGenericParametersNumberException;
import io.javarig.exception.JavaRIGInternalException;
import io.javarig.generator.collection.list.ListGenerator;
import io.javarig.testclasses.FakeList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

@Slf4j
public class ListGenerationTest {
    private RandomInstanceGenerator randomInstanceGenerator;

    @BeforeEach
    public void setUp() {
        randomInstanceGenerator = new RandomInstanceGenerator();
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
    public void shouldThrowIllegalArgumentExceptionGivenMinSizeEqualsMaxSize() {
        int size = 30;
        assertThatThrownBy(() -> {//when
            randomInstanceGenerator.generate(ArrayList.class, size, size);
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
