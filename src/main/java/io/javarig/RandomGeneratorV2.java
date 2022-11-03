package io.javarig;

import io.javarig.exception.NestedObjectRecursionException;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.Generator;

import java.lang.reflect.Type;
import java.util.Stack;
import java.util.function.Consumer;

public class RandomGeneratorV2 {
    private final Stack<Type> objectStack = new Stack<>();

    private <T> T generate(Type type, Consumer<CollectionGenerator> consumer) {
        checkForRecursion(type);
        objectStack.push(type);
        TypeEnum typeEnum = TypeEnum.fromType(type);
        Generator generator = typeEnum.generator();
        if (generator instanceof CollectionGenerator collectionGenerator) {
            consumer.accept(collectionGenerator);
        }
        T generated = (T) generator.generate(type);
        objectStack.pop();
        return generated;
    }

    public <T> T generate(Type type) {
        return generate(type, generator -> {
        });
    }

    public <T> T generate(Type type, int size) {
        return generate(type, collectionGenerator -> collectionGenerator.setSize(size));
    }

    public <T> T generate(Type type, int minSize, int maxSize) {
        return generate(type, collectionGenerator -> {
            collectionGenerator.setMinSize(minSize);
            collectionGenerator.setMaxSize(maxSize);
        });
    }


    //check if type exists in objectStack, if so then object can't be generated because there is recursion
    // in this object's fields (there a field that it's instantiation depends on a father object)
    //so NestedObjectRecursion is thrown
    private void checkForRecursion(Type type) {
        if (!objectStack.isEmpty() && objectStack.contains(type)) {
            throw new NestedObjectRecursionException(type);
        }
    }
}