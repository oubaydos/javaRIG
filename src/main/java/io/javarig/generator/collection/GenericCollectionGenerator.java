package io.javarig.generator.collection;

import io.javarig.generator.GenericTypeGenerator;


public interface GenericCollectionGenerator<T> extends GenericTypeGenerator {
    Class<? extends T> getImplementationType();
}
