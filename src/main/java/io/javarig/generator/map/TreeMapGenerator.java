package io.javarig.generator.map;

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
    protected Class<? extends Map> getImplementationType() {
        return TreeMap.class;
    }
}
