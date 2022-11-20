package io.javarig.generator.map;

import io.javarig.generator.AbstractGenerator;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.TypeBasedGenerator;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

@Getter
@Setter
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class MapGenerator extends AbstractGenerator implements TypeBasedGenerator, CollectionGenerator {
    private int minSizeInclusive = 5;
    private int maxSizeExclusive = 15;
    private Type type;

    protected abstract Class<? extends Map> getImplementationType();

    @Override
    public Map<Object, Object> generate() {
        int size = getRandom().nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        ParameterizedType parameterizedType = (ParameterizedType) getType();
        try {
            return generate(parameterizedType, size);
        } catch (Exception ignore) {
        }
        return null;
    }

    private Map<Object, Object> generate(ParameterizedType type, int size) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Type keyType = type.getActualTypeArguments()[0];
        Type valueType = type.getActualTypeArguments()[1];
        Map<Object, Object> resultedMap = getImplementationType().getConstructor().newInstance();
        for (int i = 0; i < size; i++) {
            resultedMap.put(getRandomGenerator().generate(Class.forName(keyType.getTypeName())), getRandomGenerator().generate(Class.forName(valueType.getTypeName())));
        }
        return resultedMap;
    }
}
