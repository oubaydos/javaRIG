package io.javarig.generator.list;

import io.javarig.exception.InstanceGenerationException;
import io.javarig.generator.AbstractTypeGenerator;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.TypeBasedGenerator;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Setter
@Getter
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class ListGenerator extends AbstractTypeGenerator implements CollectionGenerator, TypeBasedGenerator {
    private int minSizeInclusive = 5;
    private int maxSizeExclusive = 15;

    private Type type;

    protected abstract Class<? extends List> getImplementationType();

    @Override
    public List<Object> generate() throws InstanceGenerationException {
        int randomSize = getRandom().nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        ParameterizedType parameterizedType = (ParameterizedType) getType();
        Type listParameterType = parameterizedType.getActualTypeArguments()[0];
        return generate(listParameterType, randomSize);
    }

    @NotNull
    public List<Object> generate(Type listParameterType, int size) throws InstanceGenerationException {
        List<Object> outputList = getNewListInstance(size);
        for (int i = 0; i < size; i++) {
            outputList.add(getRandomInstanceGenerator().generate(listParameterType));
        }
        return outputList;
    }

    @NotNull
    private List getNewListInstance(int size) {
        try {
            return getImplementationType().getConstructor(int.class).newInstance(size);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new InstanceGenerationException(e);
        }
    }
}
