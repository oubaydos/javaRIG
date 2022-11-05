package io.javarig.generator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MapGenerator extends CollectionGenerator {
    @Override
    public Map<Object,Object> generate(Type type) {
        int size = random.nextInt(minSize, maxSize);
        ParameterizedType parameterizedType = (ParameterizedType) type;
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
