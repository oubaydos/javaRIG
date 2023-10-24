package io.javarig;

import io.javarig.config.JavaRIGConfig;
import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NestedObjectRecursionException;
import io.javarig.generator.TypeGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.Validate;

import java.lang.reflect.Type;
import java.util.Stack;

@RequiredArgsConstructor
@Getter
public class RandomInstanceGenerator {

    private final Stack<Type> objectStack = new Stack<>();
    private final TypeGeneratorFactory typeGeneratorFactory = new TypeGeneratorFactory();
    private final JavaRIGConfig generalConfig;
    private JavaRIGConfig oneTimeConfig = null;

    public RandomInstanceGenerator() {
        this.generalConfig = JavaRIGConfig.builder().build();
    }

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
        JavaRIGConfig oneTimeConfig = JavaRIGConfig.builder()
            .maxSizeExclusive(collectionSize + 1)
            .minSizeInclusive(collectionSize)
            .build();
        return generateWithOneTimeConfig(type, oneTimeConfig);
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
        JavaRIGConfig oneTimeConfig = JavaRIGConfig.builder()
                .maxSizeExclusive(maxSizeExclusive)
                .minSizeInclusive(minSizeInclusive)
                .build();
        return generateWithOneTimeConfig(objectType, oneTimeConfig);
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

    private <T> T generateWithOneTimeConfig(@NonNull Type type, JavaRIGConfig oneTimeConfig) {
        this.oneTimeConfig = oneTimeConfig;
        T generatedObject = generate(type);
        this.oneTimeConfig = null;
        return generatedObject;
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

}