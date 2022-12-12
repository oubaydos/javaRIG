package io.javarig.generator.map;

import io.javarig.RandomInstanceGenerator;
import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NewInstanceCreationException;
import io.javarig.generator.AbstractTypeGenerator;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.GenericTypeGenerator;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * a type generator that generates a map instance
 */
@Getter
@Setter
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class MapGenerator extends AbstractTypeGenerator implements CollectionGenerator, GenericTypeGenerator {
    private final static int NUMBER_OF_GENERIC_PARAMS = 2;
    private int minSizeInclusive = 5;
    private int maxSizeExclusive = 15;

    public MapGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    /**
     * @return the implementation type of the map
     */
    protected abstract Class<? extends Map> getImplementationType();

    @Override
    public int getNumberOfGenericParams() {
        return NUMBER_OF_GENERIC_PARAMS;
    }

    @Override
    public Map<Object, Object> generate() throws InstanceGenerationException {
        checkIfValidNumberOfGenericArguments(getType());
        int size = getRandom().nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        ParameterizedType parameterizedType = (ParameterizedType) getType();
        return generate(parameterizedType, size);

    }

    /**
     * generates a map of random key,value pairs
     *
     * @param type the actual type of the map that contains the types of the key,value pairs
     */
    private Map<Object, Object> generate(ParameterizedType type, int size) throws InstanceGenerationException {
        Type keyType = type.getActualTypeArguments()[0];
        Type valueType = type.getActualTypeArguments()[1];
        Map<Object, Object> resultedMap = getNewMapInstance();
        for (int i = 0; i < size; i++) {
            resultedMap.put(getRandomInstanceGenerator().generate(keyType), getRandomInstanceGenerator().generate(valueType));
        }
        return resultedMap;
    }

    @NotNull
    private Map<Object, Object> getNewMapInstance() {
        try {
            return getImplementationType().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new NewInstanceCreationException(getImplementationType(), e);
        }
    }
}
