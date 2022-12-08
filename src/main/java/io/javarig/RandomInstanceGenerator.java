package io.javarig;

import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NestedObjectRecursionException;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.TypeGenerator;

import java.lang.reflect.Type;
import java.util.Stack;
import java.util.function.Consumer;

public class RandomInstanceGenerator {

    private final Stack<Type> objectStack = new Stack<>();

    @SuppressWarnings({"unchecked"})
    private synchronized <T> T generate(Type type, Consumer<CollectionGenerator> collectionSizeSetter) throws InstanceGenerationException {
        checkForRecursion(type);
        objectStack.push(type);
        TypeEnum typeEnum = TypeEnum.getTypeEnum(type, this);
        TypeGenerator generator = typeEnum.generator();
        generator = setCollectionSize(generator, collectionSizeSetter);
        T generated = (T) generator.generate();
        objectStack.pop();
        return generated;
    }

    /**
     * generate a random instance of the given type
     *
     * @param type the type of the object to generate
     * @param <T>  the generic type of the object to generate
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     * default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(Type type) throws InstanceGenerationException {
        return generate(type, CollectionGenerator::resetSize);
    }

    /**
     * generate a random instance for a collection, with a fixed size
     *
     * @param type the type of the object to generate
     * @param size the size of the collection to generate
     * @param <T>  the generated object
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     * default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(Type type, int size) throws InstanceGenerationException {
        return generate(type, collectionGenerator -> collectionGenerator.setSize(size));
    }

    /**
     * generate a random instance for a collection, with size between a range
     *
     * @param type the type of the object to generate
     * @param minSizeInclusive the minimum size (inclusive)
     * @param maxSizeExclusive the maximum size (exclusive)
     * @param <T> the generic type of the object to generate
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     * default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(Type type, int minSizeInclusive, int maxSizeExclusive) throws InstanceGenerationException {
        return generate(type, collectionGenerator -> {
            collectionGenerator.setMinSizeInclusive(minSizeInclusive);
            collectionGenerator.setMaxSizeExclusive(maxSizeExclusive);
        });
    }

    /**
     * generate a random instance of a generic type
     *
     * @param type the type of the object to generate
     * @param genericTypes types of generic parameters
     * @param <T> the generated object
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     * default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(Type type, Type... genericTypes) throws InstanceGenerationException {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) type);
        return generate(parameterizedType);
    }

    /**
     * generate a random instance of a generic collection with a fixed size
     *
     * @param type the type of the object to generate
     * @param size the size of the collection to generate
     * @param genericTypes types of generic parameters
     * @param <T> the generic type of the object to generate
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     * default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(Type type, int size, Type... genericTypes) throws InstanceGenerationException {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) type);
        return generate(parameterizedType, size);
    }

    /**
     * generate a random instance of a generic collection with a size between a range
     *
     * @param type the type of the object to generate
     * @param minSizeInclusive the minimum size (inclusive)
     * @param maxSizeExclusive the maximum size (exclusive)
     * @param genericTypes types of generic parameters
     * @param <T> the generic type of the object to generate
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     * default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(Type type, int minSizeInclusive, int maxSizeExclusive, Type... genericTypes) throws InstanceGenerationException {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) type);
        return generate(parameterizedType, minSizeInclusive, maxSizeExclusive);
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

    private TypeGenerator setCollectionSize(TypeGenerator generator, Consumer<CollectionGenerator> collectionSizeSetter) {
        if (generator instanceof CollectionGenerator collectionGenerator) {
            collectionSizeSetter.accept(collectionGenerator);
            return collectionGenerator;
        }
        return generator;
    }
}