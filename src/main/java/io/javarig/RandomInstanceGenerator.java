package io.javarig;

import io.javarig.config.Configuration;
import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NestedObjectRecursionException;
import io.javarig.generator.TypeGenerator;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


import java.lang.reflect.Type;
import java.util.Stack;

@RequiredArgsConstructor
@Getter
public class RandomInstanceGenerator {

    private final Stack<Type> objectStack = new Stack<>();
    private final TypeGeneratorFactory typeGeneratorFactory = new TypeGeneratorFactory();
    private final Configuration generalConfig;
    private Configuration oneTimeConfig = null;

    public RandomInstanceGenerator() {
        this.generalConfig = Configuration.builder().build();
    }

    /**
     * generate a random instance of the given type
     * 
     * @param objectType type of the object
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for
     *                                     some reason (class doesn't have a default
     *                                     constructor , class have a non-public
     *                                     default constructor , setter cannot be
     *                                     invoked ... )
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
     * generate a random instance for a collection, with a one time configuration
     *
     * @param objectType    type of the object
     * @param oneTimeConfig a configuration for the generator that gets applied one
     *                      time and doesn't overide the general configuration
     *                      provided at the creation
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for
     *                                     some reason (class doesn't have a default
     *                                     constructor , class have a non-public
     *                                     default constructor , setter cannot be
     *                                     invoked ... )
     */
    public <T> T generate(@NonNull Type objectType,
            Configuration oneTimeConfig
    ) throws InstanceGenerationException {
        return generateWithOneTimeConfig(objectType, oneTimeConfig);
    }

    /**
     * generate a random instance of a generic type
     *
     * @param objectType   type of the object
     * @param genericTypes types of generic parameters
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for
     *                                     some reason (class doesn't have a default
     *                                     constructor , class have a non-public
     *                                     default constructor , setter cannot be
     *                                     invoked ... )
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
     * @param objectType    type of the object
     * @param oneTimeConfig a configuration for the generator that gets applied one
     *                      time and doesn't overide the general configuration
     *                      provided at the creation
     * @param genericTypes  types of generic parameters
     * @return the generated object
     * @throws InstanceGenerationException if the instance cannot be generated for
     *                                     some reason (class doesn't have
     *                                     a default constructor , class have a
     *                                     non-public default constructor , setter
     *                                     cannot be invoked ... )
     */
    public <T> T generate(
            @NonNull Type objectType,
            Configuration oneTimeConfig,
            @NonNull Type... genericTypes
    ) throws InstanceGenerationException {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) objectType);
        return generate(parameterizedType, oneTimeConfig);
    }

    private <T> T generateWithOneTimeConfig(@NonNull Type objectType, Configuration oneTimeConfig) {
        this.oneTimeConfig = oneTimeConfig;
        T generatedObject = generate(objectType);
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
    private void checkForRecursion(Type objectType) {
        if (!objectStack.isEmpty() && objectStack.contains(objectType)) {
            throw new NestedObjectRecursionException(objectType);
        }
    }



}