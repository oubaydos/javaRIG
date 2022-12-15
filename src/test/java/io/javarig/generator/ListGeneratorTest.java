package io.javarig.generator;

import io.javarig.ParameterizedTypeImpl;
import io.javarig.RandomInstanceGenerator;
import io.javarig.exception.JavaRIGInternalException;
import io.javarig.generator.collection.list.ListGenerator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ListGeneratorTest {

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
