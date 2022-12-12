package io.javarig.generator;

import io.javarig.ParameterizedTypeImpl;
import io.javarig.exception.NewInstanceCreationException;
import io.javarig.generator.map.MapGenerator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MapGeneratorTest {

    @SuppressWarnings("rawtypes")
    @Test
    public void shouldThrowNewInstanceCreationExceptionIfAMapGeneratorImplementationDoesNotHaveDefaultConstructor() {
        final Class<? extends Map> fakeMapClass = FakeMap.class;
        MapGenerator fakeMapGenerator = new MapGenerator() {
            @Override
            public Class<? extends Map> getImplementationType() {
                return fakeMapClass;
            }
        };
        ParameterizedTypeImpl typeToGenerate = new ParameterizedTypeImpl(new Type[]{String.class,String.class}, fakeMapClass);
        fakeMapGenerator.setType(typeToGenerate);

        assertThatThrownBy(fakeMapGenerator::generate)
                .isInstanceOf(NewInstanceCreationException.class)
                .hasMessage("""
                        Error invoking the default constructor for this implementation class %s,
                        please make sure that the default constructor exists and it's accessible.
                        """.formatted(fakeMapClass));
    }
}
