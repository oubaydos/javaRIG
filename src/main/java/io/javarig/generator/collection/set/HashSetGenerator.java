package io.javarig.generator.collection.set;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class HashSetGenerator extends SetGenerator{
    public HashSetGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @SuppressWarnings({"rawtypes"})
    @Override
    public Class<? extends Set> getImplementationType() {
        return HashSet.class;
    }
}
