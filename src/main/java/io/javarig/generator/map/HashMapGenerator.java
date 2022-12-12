package io.javarig.generator.map;

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
    protected Class<? extends Map> getImplementationType() {
        return HashMap.class ;
    }
}
