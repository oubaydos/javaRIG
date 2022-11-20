package io.javarig;

import io.javarig.exception.NestedObjectRecursionException;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.Generator;

import java.lang.reflect.Type;
import java.util.Stack;
import java.util.function.Consumer;

public class RandomGenerator {

    private final Stack<Type> objectStack = new Stack<>();

    @SuppressWarnings({"unchecked"})
    private synchronized <T> T generate(Type type, Consumer<CollectionGenerator> collectionSizeSetter) {
        checkForRecursion(type);
        objectStack.push(type);
        TypeEnum typeEnum = TypeEnum.getTypeEnum(type,this);
        Generator generator = typeEnum.generator();
        generator = setCollectionSize(generator, collectionSizeSetter);
        T generated = (T) generator.generate();
        objectStack.pop();
        return generated;
    }

    public <T> T generate(Type type) {
        return generate(type, ignore -> {
        });
    }

    public <T> T generate(Type type, int size) {
        return generate(type, collectionGenerator -> collectionGenerator.setSize(size));
    }

    public <T> T generate(Type type, int minSizeInclusive, int maxSizeExclusive) {
        return generate(type, collectionGenerator -> {
            collectionGenerator.setMinSizeInclusive(minSizeInclusive);
            collectionGenerator.setMaxSizeExclusive(maxSizeExclusive);
        });
    }

    public <T> T generate(Type type, Type... genericTypes) {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) type);
        return generate(parameterizedType);
    }

    public <T> T generate(Type type, int size, Type... genericTypes) {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) type);
        return generate(parameterizedType, size);
    }

    public <T> T generate(Type type, int minSize, int maxSize, Type... genericTypes) {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) type);
        return generate(parameterizedType, minSize, maxSize);
    }

    /**
     * check if type exists in objectStack, if so then object can't be generated because there is recursion
     * in this object's fields (there is a field that it's instantiation depends on owner object)
     * so NestedObjectRecursion is thrown
     *
     * @param type - a type to search for in the stack
     */
    private void checkForRecursion(Type type) {
        if (!objectStack.isEmpty() && objectStack.contains(type)) {
            throw new NestedObjectRecursionException(type);
        }
    }

    private Generator setCollectionSize(Generator generator, Consumer<CollectionGenerator> collectionSizeSetter) {
        if (generator instanceof CollectionGenerator collectionGenerator) {
            collectionSizeSetter.accept(collectionGenerator);
            return collectionGenerator;
        }
        return generator;
    }
}