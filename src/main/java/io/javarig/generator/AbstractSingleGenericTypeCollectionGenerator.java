package io.javarig.generator;

import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NewInstanceCreationException;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

@Setter
@Getter
public abstract class AbstractSingleGenericTypeCollectionGenerator<T extends Collection> extends AbstractTypeGenerator implements GenericCollectionGenerator<T> {
    private final static int NUMBER_OF_GENERIC_PARAMS = 1;
    private int minSizeInclusive = DEFAULT_MIN_SIZE_INCLUSIVE;
    private int maxSizeExclusive = DEFAULT_MAX_SIZE_EXCLUSIVE;
    private Type type;
    @Override
    public int getNumberOfGenericParams() {
        return NUMBER_OF_GENERIC_PARAMS;
    }
    @Override
    public T generate() throws InstanceGenerationException {
        int randomSize = getRandom().nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        checkIfValidNumberOfGenericArguments(type);
        ParameterizedType parameterizedType = (ParameterizedType) getType();
        Type listParameterType = parameterizedType.getActualTypeArguments()[0];
        return generate(listParameterType, randomSize);
    }

    /**
     * generates a collection of random values
     *
     * @param collectionParameterType the type of the values inside the collection
     */
    @NotNull
    public T generate(Type collectionParameterType, int size) throws InstanceGenerationException {
        T outputList = getNewCollectionInstance();
        for (int i = 0; i < size; i++) {
            outputList.add(getRandomInstanceGenerator().generate(collectionParameterType));
        }
        return outputList;
    }

    @NotNull
    private T getNewCollectionInstance() {
        try {
            return getImplementationType().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new NewInstanceCreationException(getImplementationType(), e);
        }
    }
}
