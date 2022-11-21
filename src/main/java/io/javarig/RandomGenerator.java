package io.javarig;

import io.javarig.exception.NestedObjectRecursionException;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.Generator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Stack;
import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomGenerator {

    private static RandomGenerator randomGenerator;
    private final Stack<Type> objectStack = new Stack<>();
    public static synchronized RandomGenerator getInstance() {
        if (randomGenerator == null) {
            randomGenerator = new RandomGenerator();
        }
        return randomGenerator;
    }

    @SuppressWarnings({"unchecked"})
    private synchronized <T> T generate(Type type, Consumer<CollectionGenerator> collectionSizeSetter) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        checkForRecursion(type);
        objectStack.push(type);
        TypeEnum typeEnum = TypeEnum.fromType(type);
        Generator generator = typeEnum.generator();
        generator = setCollectionSize(generator, collectionSizeSetter);
        T generated = (T) generator.generate();
        objectStack.pop();
        return generated;
    }

    public <T> T generate(Type type) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        return generate(type, ignore -> {
        });
    }

    public <T> T generate(Type type, int size) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        return generate(type, collectionGenerator -> collectionGenerator.setSize(size));
    }

    public <T> T generate(Type type, int minSizeInclusive, int maxSizeExclusive) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        return generate(type, collectionGenerator -> {
            collectionGenerator.setMinSizeInclusive(minSizeInclusive);
            collectionGenerator.setMaxSizeExclusive(maxSizeExclusive);
        });
    }

    public <T> T generate(Type type, Type... genericTypes) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) type);
        return generate(parameterizedType);
    }

    public <T> T generate(Type type, int size, Type... genericTypes) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) type);
        return generate(parameterizedType, size);
    }

    public <T> T generate(Type type, int minSize, int maxSize, Type... genericTypes) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) type);
        return generate(parameterizedType, minSize, maxSize);
    }

    /**
     * check if type exists in objectStack, if so then object can't be generated because there is recursion
     * in this object's fields (there a field that it's instantiation depends on a father object)
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