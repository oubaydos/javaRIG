package io.javarig;

import io.javarig.config.Configuration;
import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NestedObjectRecursionException;
import io.javarig.generator.TypeGenerator;
import io.javarig.util.Utils;
import io.javarig.util.Validators;
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

    public RandomInstanceGenerator(Configuration generalConfig, Configuration oneTimeConfig) {
        this.generalConfig = generalConfig;
        this.oneTimeConfig = oneTimeConfig;
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
    @SuppressWarnings({ "unchecked" })
    public <T> T generate(@NonNull Type objectType) throws InstanceGenerationException {
        checkForRecursion(objectType);
        objectStack.push(objectType);
        TypeGenerator generator = typeGeneratorFactory.getGenerator(objectType, this);
        T generated = (T) generator.generate();
        objectStack.pop();
        clearOneTimeConfig();
        return generated;
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
            @NonNull Type... genericTypes) throws InstanceGenerationException {
        Type parameterizedType = new ParameterizedTypeImpl(genericTypes, (Class<?>) objectType);
        return generate(parameterizedType);
    }

    /**
     * check if type exists in objectStack, if so then object can't be generated
     * because there is recursion
     * in this object's fields (there is a field that it's instantiation depends on
     * owner object)
     * so NestedObjectRecursion is thrown
     *
     * @param type - a type to search for in the stack
     */
    private void checkForRecursion(Type objectType) {
        if (!objectStack.isEmpty() && objectStack.contains(objectType)) {
            throw new NestedObjectRecursionException(objectType);
        }
    }

    private void clearOneTimeConfig() {
        if (objectStack.empty()) {
            this.oneTimeConfig = null;
        }
    }

    public RandomInstanceGenerator withSize(int size) {
        Validators.validateSize(size);
        Configuration oneTimeConfig = generalConfig.withMaxSizeExclusive(size + 1).withMinSizeInclusive(size);
        return new RandomInstanceGenerator(generalConfig, oneTimeConfig);
    }

    public RandomInstanceGenerator withSize(int minSizeInclusive, int maxSizeExclusive) {
        Validators.validateSize(minSizeInclusive, maxSizeExclusive);
        Configuration oneTimeConfig = generalConfig.withMaxSizeExclusive(maxSizeExclusive)
                .withMinSizeInclusive(minSizeInclusive);
        return new RandomInstanceGenerator(generalConfig, oneTimeConfig);
    }

    public RandomInstanceGenerator withRegexPattern(String regexPattern) {
        Validators.validateRegexPattern(Utils.removeUnsupportedRegexCharacters(regexPattern));
        return new RandomInstanceGenerator(generalConfig, generalConfig.withRegexPattern(regexPattern));
    }

    public RandomInstanceGenerator withOneTimeConfig(Configuration oneTimeConfig) {
        return new RandomInstanceGenerator(generalConfig, oneTimeConfig);
    }
}