package io.javarig.generator.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapGenerator extends MapGenerator {
    @Override
    @SuppressWarnings({"rawtypes"})
    protected Class<? extends Map> getImplementationType() {
        return HashMap.class ;
    }
}
