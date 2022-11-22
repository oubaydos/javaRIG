package io.javarig.generator;

import io.javarig.exception.InstanceGenerationException;
import io.javarig.exception.NoDefaultConstructorException;
import io.javarig.exception.NoFieldAssociatedToSetter;
import io.javarig.util.Utils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Slf4j
public class ObjectGenerator extends AbstractGenerator implements TypeBasedGenerator {
    private Type type;

    @Override
    public Object generate() throws InstanceGenerationException {
        Class<?> objectClass = (Class<?>) getType();
        Object object = this.createNewInstance(objectClass);
        log.info("generating object of type {} ...", objectClass.getName());
        generateFields(object, objectClass);
        log.info("created object {}", object);
        return object;
    }

    private void generateFields(Object object, Class<?> objectClass) {

        List<Method> setters = Arrays.stream(objectClass.getDeclaredMethods())
                .filter(method -> method.getName().startsWith("set"))
                .toList();

        for (Method setter : setters) {
            String fieldName = Utils.getFieldNameFromSetterMethodName(setter.getName());
            try {
                Field field = objectClass.getDeclaredField(fieldName);
                generateField(object, setter, field);
            } catch (NoSuchFieldException e) {
                throw new NoFieldAssociatedToSetter(fieldName, setter.getName(), e);
            }
        }

    }

    private void generateField(Object object, Method setter, Field field) {
        Type type = field.getGenericType();
        Object generated = getRandomGenerator().generate(type);
        try {
            setter.invoke(object, generated);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InstanceGenerationException(e);
        }
    }

    private Object createNewInstance(Class<?> objectClass) {
        try {
            return objectClass.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new NoDefaultConstructorException(objectClass, e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new InstanceGenerationException(e);
        }
    }
}
