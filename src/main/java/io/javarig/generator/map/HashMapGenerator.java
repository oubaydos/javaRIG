package io.javarig.generator.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapGenerator extends MapGenerator {
    @Override
    @SuppressWarnings({"rawtypes"})
    public Class<? extends Map> getImplementationType() {
        return HashMap.class ;
    }
}
