package io.javarig.generator.list;

import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NewInstanceCreationException;
import io.javarig.generator.AbstractTypeGenerator;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.GenericTypeGenerator;
import io.javarig.generator.TypeBasedGenerator;
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
public abstract class ListGenerator extends AbstractTypeGenerator implements CollectionGenerator, TypeBasedGenerator, GenericTypeGenerator {
    private final static int NUMBER_OF_GENERIC_PARAMS = 1;
    private int minSizeInclusive = 5;
    private int maxSizeExclusive = 15;
    private Type type;

    protected abstract Class<? extends List> getImplementationType();

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
