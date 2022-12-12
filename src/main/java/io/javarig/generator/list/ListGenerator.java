package io.javarig.generator.list;

import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NewInstanceCreationException;
import io.javarig.generator.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * a type generator that generates a list instance
 */
@Setter
@Getter
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class ListGenerator extends AbstractTypeGenerator implements GenericCollectionGenerator<List>, TypeBasedGenerator {
    private final static int NUMBER_OF_GENERIC_PARAMS = 1;
    private int minSizeInclusive = DEFAULT_MIN_SIZE_INCLUSIVE;
    private int maxSizeExclusive = DEFAULT_MAX_SIZE_EXCLUSIVE;
    private Type type;


    @Override
    public int getNumberOfGenericParams() {
        return NUMBER_OF_GENERIC_PARAMS;
    }

    @Override
    public List<Object> generate() throws InstanceGenerationException {
        int randomSize = getRandom().nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        checkIfValidNumberOfGenericArguments(type);
        ParameterizedType parameterizedType = (ParameterizedType) getType();
        Type listParameterType = parameterizedType.getActualTypeArguments()[0];
        return generate(listParameterType, randomSize);
    }
    /**
     * generates a list of random values
     *
     * @param listParameterType the type of the values inside the list
     */
    @NotNull
    public List<Object> generate(Type listParameterType, int size) throws InstanceGenerationException {
        List<Object> outputList = getNewListInstance();
        for (int i = 0; i < size; i++) {
            outputList.add(getRandomInstanceGenerator().generate(listParameterType));
        }
        return outputList;
    }

    @NotNull
    private List getNewListInstance() {
        try {
            return getImplementationType().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new NewInstanceCreationException(getImplementationType(), e);
        }
    }
}
