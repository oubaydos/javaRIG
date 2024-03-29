package io.javarig.generator;

import io.javarig.util.GenericTypes;
import io.javarig.ParameterizedTypeImpl;
import io.javarig.RandomInstanceGenerator;
import io.javarig.exception.AbstractClassInstantiationException;
import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.InvocationSetterException;
import io.javarig.exception.NoAccessibleDefaultConstructorException;
import io.javarig.util.Utils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.javarig.util.Utils.getOwnOrInheritedFieldByName;

@Getter
@Setter
@Slf4j
public class ObjectGenerator extends TypeGenerator {
    private static final String SETTER_PREFIX = "set";
    private Map<String,Type> genericTypesMap = new HashMap<>();

    public ObjectGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Object generate() throws InstanceGenerationException {
        Type objectType = getType();
        Class<?> objectClass;
        if(objectType instanceof ParameterizedType parameterizedType){
            objectClass = (Class<?>) parameterizedType.getRawType();
            constructGenericTypesMap(objectClass, parameterizedType);
        } else {
            objectClass = (Class<?>) objectType;
        }
        Object generatedObject = getNewObjectInstance(objectClass);
        log.info("generating object of type {} ...", objectClass.getName());
        generateFields(generatedObject, objectClass);
        log.info("created object {}", generatedObject);
        return generatedObject;
    }

    private void constructGenericTypesMap(Class<?> objectClass, ParameterizedType parameterizedType) {
        List<Type> typeParametersValues = Arrays.asList(parameterizedType.getActualTypeArguments());
        List<String> typeParametersKeys = Arrays.stream(objectClass.getTypeParameters())
                .map(TypeVariable::getTypeName)
                .toList();
        genericTypesMap = IntStream.range(0, typeParametersKeys.size())
                .boxed()
                .collect(Collectors.toMap(typeParametersKeys::get, typeParametersValues::get));
    }

    private void generateFields(Object generatedObject, Class<?> objectClass) throws InstanceGenerationException {
        List<Method> setters = getSetters(objectClass);
        setters.forEach((setter) -> generateFieldWithSetter(generatedObject, objectClass, setter));
    }

    private static List<Method> getSetters(Class<?> objectClass) {
        return Arrays.stream(objectClass.getMethods())
                .filter(method -> method.getName().startsWith(SETTER_PREFIX))
                .toList();
    }

    private void generateFieldWithSetter(Object generatedObject, Class<?> objectClass, Method setter) {
        String fieldName = Utils.getFieldNameFromSetterMethodName(setter.getName(), SETTER_PREFIX);
        try {
            Field field = getOwnOrInheritedFieldByName(objectClass, fieldName);
            generateField(generatedObject, setter, field);
        } catch (NoSuchFieldException ignore) {
            log.warn("no such field with name {} for setter {}", fieldName, setter.getName());
        }
    }

    private void generateField(Object generatedObject, Method setter, Field field) throws InstanceGenerationException {
        Type type = field.getGenericType();
        if(type instanceof ParameterizedType parameterizedType){
            type = resolveTypeArguments(parameterizedType);
        }
        Object generatedField = getRandomInstanceGenerator().generate(GenericTypes.resolve(type, genericTypesMap));
        try {
            setter.invoke(generatedObject, generatedField);
        } catch (IllegalAccessException ignore) {
            // this will be ignored because if the setter is not accessible (i.e. has a non-public access modifier)
            // we don't want to do anything
            log.warn("setter {} in class {} is not accessible", setter.getName(), generatedObject.getClass().getName());
        } catch (InvocationTargetException e) {
            throw new InvocationSetterException(setter.getName(), generatedObject.getClass().getName(), e);
        }
    }

    private Type resolveTypeArguments(Type type) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        List<Type> typeArguments = Arrays.stream(parameterizedType.getActualTypeArguments())
                .map((typeArgument -> GenericTypes.resolve(typeArgument, genericTypesMap)))
                .toList();
        Type[] typeArgumentsArray = new Type[typeArguments.size()];
        return new ParameterizedTypeImpl(typeArguments.toArray(typeArgumentsArray),
                (Class<?>) ((ParameterizedType) type).getRawType());
    }

    private Object getNewObjectInstance(Class<?> objectClass) throws InstanceGenerationException {
        try {
            return objectClass.getConstructor().newInstance();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new InstanceGenerationException(e);
        } catch (NoSuchMethodException e) {
            throw new NoAccessibleDefaultConstructorException(objectClass, e);
        } catch (InstantiationException e) {
            throw new AbstractClassInstantiationException(objectClass.getName(), e);
        }
    }
}
