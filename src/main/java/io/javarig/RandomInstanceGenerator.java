package io.javarig;

import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NestedObjectRecursionException;
import io.javarig.generator.TypeGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Type;
import java.util.Stack;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RandomInstanceGenerator {
    
    private final Stack<Type> objectStack = new Stack<>();
    private final TypeGeneratorFactory typeGeneratorFactory = new TypeGeneratorFactory();

    // default values
    public final static int DEFAULT_MAX_SIZE_EXCLUSIVE = 15;
    public final static int DEFAULT_MIN_SIZE_INCLUSIVE = 5;

    // configurations 
    @Builder.Default private int maxSizeExclusive = DEFAULT_MAX_SIZE_EXCLUSIVE;
    @Builder.Default private int minSizeInclusive = DEFAULT_MIN_SIZE_INCLUSIVE;

    /**
     * generate a random instance of the given type
     *
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     * a default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    @SuppressWarnings({"unchecked"})
    public <T> T generate(@NonNull Type objectType) throws InstanceGenerationException {
        checkForRecursion(objectType);
        objectStack.push(objectType);
        TypeGenerator generator = typeGeneratorFactory.getGenerator(objectType, this);
        T generated = (T) generator.generate();
        objectStack.pop();
        return generated;
    }

    /**
     * generate a random instance for a collection, with a fixed size
     *
     * @param collectionSize the size of the collection to generate
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     * a default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(
            @NonNull Type type,
            int collectionSize
    ) throws InstanceGenerationException {
        validateSize(collectionSize);
        setSize(collectionSize);
        return generate(type);
    }

    /**
     * generate a random instance for a collection, with size between a range
     *
     * @param <T> the generic type of the object to generate
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     * a default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(@NonNull Type objectType,
                          int minSizeInclusive,
                          int maxSizeExclusive
    ) throws InstanceGenerationException {
        validateSize(minSizeInclusive, maxSizeExclusive);
        this.maxSizeExclusive = maxSizeExclusive;
        this.minSizeInclusive = minSizeInclusive;
        return generate(objectType);
    }

    /**
     * generate a random instance of a generic type
     *
     * @param genericTypes types of generic parameters
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for some reason (class doesn't have
     * a default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(
            @NonNull Type objectType,
            @NonNull Type... genericTypes
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
     * a default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(
            @NonNull Type type,
            int collectionSize,
            @NonNull Type... genericTypes
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
     * a default constructor , class have a non-public default constructor , setter cannot be invoked ... )
     */
    public <T> T generate(
            @NonNull Type type,
            int minSizeInclusive,
            int maxSizeExclusive,
            @NonNull Type... genericTypes
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

    private void validateSize(int minSizeInclusive, int maxSizeExclusive) {
        Validate.isTrue(maxSizeExclusive > minSizeInclusive, "Start value must be smaller than end value.");
        Validate.isTrue(minSizeInclusive >= 0, "Both range values must be non-negative.");
    }

    private void validateSize(int size) {
        Validate.isTrue(size >= 0, "Size must be non-negative.");
    }

    private void setSize(int size){
        this.maxSizeExclusive = size + 1;
        this.minSizeInclusive = size;
    }

}