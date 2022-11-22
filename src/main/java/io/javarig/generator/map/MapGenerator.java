package io.javarig.generator.map;

import io.javarig.exception.InstanceGenerationException;
import io.javarig.generator.AbstractGenerator;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.TypeBasedGenerator;
import lombok.Getter;
import lombok.Setter;

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
    public Map<Object, Object> generate() throws InstanceGenerationException {
        int size = getRandom().nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        ParameterizedType parameterizedType = (ParameterizedType) getType();
        return generate(parameterizedType, size);

    }

    public Map<Object, Object> generate(ParameterizedType type, int size) throws InstanceGenerationException{
        Type keyType = type.getActualTypeArguments()[0];
        Type valueType = type.getActualTypeArguments()[1];
        try {
            Map<Object, Object> resultedMap = getImplementationType().getConstructor().newInstance();
            for (int i = 0; i < size; i++) {
                resultedMap.put(
                        getRandomGenerator().generate(keyType),
                        getRandomGenerator().generate(valueType)
                );
            }
            return resultedMap;
        } catch (ReflectiveOperationException e) {
            throw new InstanceGenerationException(e);
        }
    }
}
