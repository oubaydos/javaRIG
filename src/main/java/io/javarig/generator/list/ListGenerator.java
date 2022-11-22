package io.javarig.generator.list;

import io.javarig.exception.InstanceGenerationException;
import io.javarig.generator.AbstractGenerator;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.TypeBasedGenerator;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Setter
@Getter
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class ListGenerator extends AbstractGenerator implements CollectionGenerator, TypeBasedGenerator {
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
    public List<Object> generate(Type listParameterType, int size) throws InstanceGenerationException{
        try {
            List<Object> outputList = getImplementationType().getConstructor(int.class).newInstance(size);
            for (int i = 0; i < size; i++) {
                outputList.add(getRandomGenerator().generate(listParameterType));
            }
            return outputList;
        } catch (ReflectiveOperationException e) {
            throw new InstanceGenerationException(e);
        }
    }
}
