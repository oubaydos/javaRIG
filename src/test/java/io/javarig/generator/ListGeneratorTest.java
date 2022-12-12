package io.javarig.generator;

import io.javarig.ParameterizedTypeImpl;
import io.javarig.exception.NewInstanceCreationException;
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
        ListGenerator fakeListGenerator = new ListGenerator() {
            @Override
            public Class<? extends List> getImplementationType() {
                return fakeListClass;
            }
        };
        ParameterizedTypeImpl typeToGenerate = new ParameterizedTypeImpl(new Type[]{String.class}, fakeListClass);
        fakeListGenerator.setType(typeToGenerate);

        assertThatThrownBy(fakeListGenerator::generate)
                .isInstanceOf(NewInstanceCreationException.class)
                .hasMessage("""
                        Error invoking the default constructor for this implementation class %s,
                        please make sure that the default constructor exists and it's accessible.
                        """.formatted(fakeListClass));
    }
}
