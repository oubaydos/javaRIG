package io.javarig.generator.collection.map;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapGenerator extends MapGenerator {

    @Override
    @SuppressWarnings({"rawtypes"})
    public Class<? extends Map> getImplementationType() {
        return TreeMap.class;
    }
}