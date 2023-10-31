package io.javarig;


public interface ImplementationProvider<T> {
    Class<? extends T> getImplementationType();
}
