package io.javarig.generator;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapGenerator extends MapGenerator{

    @Override
    @SuppressWarnings("rawtypes")
    protected Class<? extends Map> getImplementationType() {
        return TreeMap.class;
    }
}
