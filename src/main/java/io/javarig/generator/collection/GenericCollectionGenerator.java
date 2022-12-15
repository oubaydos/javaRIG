package io.javarig.generator.collection;

import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.GenericTypeGenerator;


public interface GenericCollectionGenerator<T> extends CollectionGenerator, GenericTypeGenerator {
    Class<? extends T> getImplementationType();

}
