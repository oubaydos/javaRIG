package io.javarig.generator;

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
public class ObjectGenerator implements Generator {
    private Type type;

    @Override
    public Object generate() {
        try {
            Class<?> objectClass = (Class<?>) getType();
            Object object = objectClass.getConstructor().newInstance();
            log.info("generating object of type {} ...", objectClass.getName());
            generateFields(object, objectClass);
            log.info("created object {}", object);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void generateFields(Object object, Class<?> objectClass) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {

        List<Method> setters = Arrays.stream(objectClass.getDeclaredMethods())
                .filter(method -> method.getName().startsWith("set"))
                .toList();

        for (Method setter : setters) {
            String fieldName = Utils.getFieldNameFromSetterMethodName(setter.getName());
            Field field = objectClass.getDeclaredField(fieldName);
            generateField(object, setter, field);
        }

    }

    private void generateField(Object object, Method setter, Field field) throws InvocationTargetException, IllegalAccessException {
        Type type = field.getGenericType();
        Object generated = randomGenerator.generate(type);
        setter.invoke(object, generated);
    }
}
