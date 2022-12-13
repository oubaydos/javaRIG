package io.javarig;

import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NestedObjectRecursionException;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.TypeGenerator;
import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

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
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     *                                     default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(@NotNull Type objectType) throws InstanceGenerationException {
        return generate(objectType, ignore -> {
        });
    }

    /**
     * generate a random instance for a collection, with a fixed size
     *
     * @param collectionSize the size of the collection to generate
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     *                                     default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(
            @NotNull Type type,
            @Range(from = 0, to = Integer.MAX_VALUE) int collectionSize
    ) throws InstanceGenerationException {
        return generate(type, collectionGenerator -> collectionGenerator.setSize(collectionSize));
    }

    /**
     * generate a random instance for a collection, with size between a range
     *
     * @param <T> the generic type of the object to generate
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     *                                     default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(@NotNull Type objectType,
                          @Range(from = 0, to = Integer.MAX_VALUE) int minSizeInclusive,
                          @Range(from = 0, to = Integer.MAX_VALUE) int maxSizeExclusive
    ) throws InstanceGenerationException {
        if (minSizeInclusive > maxSizeExclusive) {
            throw new IllegalArgumentException("minSizeInclusive must be less than maxSizeExclusive");
        }
        return generate(objectType, collectionGenerator -> {
            collectionGenerator.setMinSizeInclusive(minSizeInclusive);
            collectionGenerator.setMaxSizeExclusive(maxSizeExclusive);
        });
    }

    /**
     * generate a random instance of a generic type
     *
     * @param genericTypes types of generic parameters
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     *                                     default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(
            @NotNull Type objectType,
            @NotEmpty Type... genericTypes
    ) throws InstanceGenerationException {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) objectType);
        return generate(parameterizedType);
    }

    /**
     * generate a random instance of a generic collection with a fixed size
     *
     * @param genericTypes types of generic parameters
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     *                                     default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(
            @NotNull Type type,
            @Range(from = 0, to = Integer.MAX_VALUE) int collectionSize,
            @NotEmpty Type... genericTypes
    ) throws InstanceGenerationException {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) type);
        return generate(parameterizedType, collectionSize);
    }

    /**
     * generate a random instance of a generic collection with a size between a range
     *
     * @param genericTypes types of generic parameters
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     *                                     default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(
            @NotNull Type type,
            @Range(from = 0, to = Integer.MAX_VALUE) int minSizeInclusive,
            @Range(from = 0, to = Integer.MAX_VALUE) int maxSizeExclusive,
            @NotEmpty Type... genericTypes
    ) throws InstanceGenerationException {
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