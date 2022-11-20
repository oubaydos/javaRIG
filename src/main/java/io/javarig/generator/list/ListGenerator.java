package io.javarig.generator.list;

import io.javarig.generator.AbstractGenerator;
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
public abstract class ListGenerator extends AbstractGenerator implements CollectionGenerator, TypeBasedGenerator {
    private int minSizeInclusive = 5;
    private int maxSizeExclusive = 15;

    private Type type;

    protected abstract Class<? extends List> getImplementationType();

    @Override
    public List<Object> generate() {
        int randomSize = getRandom().nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        ParameterizedType parameterizedType = (ParameterizedType) getType();
        Type inputListType = parameterizedType.getActualTypeArguments()[0];
        try {
            return generate(inputListType, randomSize);
        } catch (Exception ignore) {
        }
        return null;
    }

    @NotNull
    private List<Object> generate(Type inputListType, int randomSize) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        List<Object> outputList = getImplementationType().getConstructor(int.class).newInstance(randomSize);
        for (int i = 0; i < randomSize; i++) {
            outputList.add(getRandomGenerator().generate(Class.forName(inputListType.getTypeName())));
        }
        return outputList;
    }
}
