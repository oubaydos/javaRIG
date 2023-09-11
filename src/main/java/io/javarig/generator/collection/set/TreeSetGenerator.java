package io.javarig.generator.collection.set;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetGenerator extends SetGenerator{
    public TreeSetGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @SuppressWarnings({"rawtypes"})
    @Override
    public Class<? extends Set> getImplementationType() {
        return TreeSet.class;
    }
}
