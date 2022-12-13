package io.javarig.generator.collection.list;

import io.javarig.RandomInstanceGenerator;
import io.javarig.generator.collection.SingleGenericTypeCollectionGenerator;

import java.lang.reflect.Type;
import java.util.List;

/**
 * a type generator that generates a list instance
 */

@SuppressWarnings({"rawtypes"})
public abstract class ListGenerator extends SingleGenericTypeCollectionGenerator<List> {

    public ListGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }
}
