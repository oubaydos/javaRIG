package io.javarig.generator.collection.map;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapGenerator extends MapGenerator {

    public TreeMapGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    @SuppressWarnings({"rawtypes"})
    public Class<? extends Map> getImplementationType() {
        return TreeMap.class;
    }
}
