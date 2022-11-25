package io.javarig.generator;

import io.javarig.exception.*;
import io.javarig.util.Utils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Slf4j
public class ObjectGenerator extends AbstractTypeGenerator implements TypeBasedGenerator {
    private static final String SETTER_PREFIX = "set";
    private Type type;

    @Override
    public Object generate() throws InstanceGenerationException {
        Class<?> objectClass = (Class<?>) getType();
        Object generatedObject = createNewInstance(objectClass);
        log.info("generating object of type {} ...", objectClass.getName());
        generateFields(generatedObject, objectClass);
        log.info("created object {}", generatedObject);
        return generatedObject;
    }

    private void generateFields(Object generatedObject, Class<?> objectClass) throws InstanceGenerationException {
        List<Method> setters = getSetters(objectClass);
        setters.forEach((setter) -> generateFieldWithSetter(generatedObject, objectClass, setter));
    }

    @NotNull
    private static List<Method> getSetters(Class<?> objectClass) {
        return Arrays.stream(objectClass.getDeclaredMethods())
                .filter(method -> method.getName().startsWith(SETTER_PREFIX))
                .toList();
    }

    private void generateFieldWithSetter(Object generatedObject, Class<?> objectClass, Method setter) {
        String fieldName = Utils.getFieldNameFromSetterMethodName(setter.getName());
        try {
            Field field = objectClass.getDeclaredField(fieldName);
            generateField(generatedObject, setter, field);
        } catch (NoSuchFieldException ignore) {
            log.warn("no such field with name {} for setter {}", fieldName, setter.getName());
        }
    }

    private void generateField(Object generatedObject, Method setter, Field field) throws InstanceGenerationException {
        Type type = field.getGenericType();
        Object generatedField = getRandomInstanceGenerator().generate(type);
        try {
            setter.invoke(generatedObject, generatedField);
        } catch (IllegalAccessException ignore) {
            //this will be ignored because if the setter is not accessible, we don't want to do anything
            log.warn("setter {} in class {} is not accessible", setter.getName(), generatedObject.getClass().getName());
        } catch (InvocationTargetException e) {
            throw new InvocationSetterException(setter.getName(), generatedObject.getClass().getName(), e);
        }
    }

    private Object createNewInstance(Class<?> objectClass) throws InstanceGenerationException {
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
