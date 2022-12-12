package io.javarig.generator.collection;

import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.GenericTypeGenerator;
import io.javarig.generator.TypeBasedGenerator;

public interface GenericCollectionGenerator<T> extends CollectionGenerator, GenericTypeGenerator, TypeBasedGenerator {
    Class<? extends T> getImplementationType();

}
