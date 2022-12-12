package io.javarig.generator;

public interface GenericCollectionGenerator<T> extends CollectionGenerator, GenericTypeGenerator{
    Class<? extends T> getImplementationType();

}
