package io.javarig.generator.collection.map;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class HashMapGenerator extends MapGenerator {
    public HashMapGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    @SuppressWarnings({"rawtypes"})
    public Class<? extends Map> getImplementationType() {
        return HashMap.class ;
    }
}
