package io.javarig.generator;

public interface GenericCollectionGenerator<T> extends CollectionGenerator, GenericTypeGenerator, TypeBasedGenerator {
    Class<? extends T> getImplementationType();

}
