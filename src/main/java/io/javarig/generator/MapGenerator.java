package io.javarig.generator;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class MapGenerator implements GenericTypeGenerator, CollectionGenerator {
    private int minSize = 5;
    private int maxSize = 15;
    private ParameterizedType type;
    @Override
    public Map<Object,Object> generate() {
        int size = random.nextInt(getMinSize(), getMaxSize());
        ParameterizedType parameterizedType = getType();
        Type keyType = parameterizedType.getActualTypeArguments()[0];
        Type valueType = parameterizedType.getActualTypeArguments()[1];
        Map<Object, Object> resultedMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            try {
                resultedMap.put(randomGenerator.generate(Class.forName(keyType.getTypeName())),
                        randomGenerator.generate(Class.forName(valueType.getTypeName())));
            } catch (Exception ignore) {
            }
        }
        return resultedMap;
    }
}
