package io.javarig.generator.collection.set;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetGenerator extends SetGenerator{
    public LinkedHashSetGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @SuppressWarnings({"rawtypes"})
    @Override
    public Class<? extends Set> getImplementationType() {
        return LinkedHashSet.class;
    }
}
