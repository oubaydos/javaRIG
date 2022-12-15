package io.javarig.generator;

import io.javarig.ParameterizedTypeImpl;
import io.javarig.RandomInstanceGenerator;
import io.javarig.exception.JavaRIGInternalException;
import io.javarig.generator.collection.map.MapGenerator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MapGeneratorTest {

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
