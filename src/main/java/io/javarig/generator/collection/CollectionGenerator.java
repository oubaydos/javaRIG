package io.javarig.generator.collection;

import io.javarig.GenericType;
import io.javarig.ImplementationProvider;
import io.javarig.RandomInstanceGenerator;
import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.JavaRIGInternalException;
import io.javarig.generator.TypeGenerator;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

@Setter
@Getter
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class CollectionGenerator<T extends Collection> extends TypeGenerator implements ImplementationProvider<T>, GenericType {
    private final static int NUMBER_OF_GENERIC_PARAMS = 1;

    public CollectionGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public int getNumberOfGenericParams() {
        return NUMBER_OF_GENERIC_PARAMS;
    }

    @Override
    public T generate() throws InstanceGenerationException {
        int randomSize = getRandom().nextInt(getConfig().getMinSizeInclusive(), getConfig().getMaxSizeExclusive());
        checkIfValidNumberOfGenericArguments(getType());
        ParameterizedType parameterizedType = (ParameterizedType) getType();
        Type listParameterType = parameterizedType.getActualTypeArguments()[0];
        return generate(listParameterType, randomSize);
    }

    /**
     * generates a collection of random values
     *
     * @param collectionParameterType the type of the values inside the collection
     */
    public T generate(Type collectionParameterType, int size) throws InstanceGenerationException {
        T outputList = getNewCollectionInstance();
        for (int i = 0; i < size; i++) {
            outputList.add(getRandomInstanceGenerator().generate(collectionParameterType));
        }
        return outputList;
    }

    private T getNewCollectionInstance() throws JavaRIGInternalException {
        try {
            return getImplementationType().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new JavaRIGInternalException(e);
        }
    }
}
