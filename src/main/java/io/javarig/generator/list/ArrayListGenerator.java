package io.javarig.generator.list;

import io.javarig.RandomInstanceGenerator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ArrayListGenerator extends ListGenerator {
    public ArrayListGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Class<? extends List> getImplementationType() {
        return ArrayList.class;
    }
}
