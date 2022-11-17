package io.javarig;

import io.javarig.generator.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

public enum TypeEnum {

    INTEGER(List.of(Integer.class, int.class), new IntegerGenerator()),
    STRING(List.of(String.class), new StringGenerator()),
    BYTE(List.of(Byte.class, byte.class), new ByteGenerator()),
    BYTE_ARRAY(List.of(Byte[].class, byte[].class), new ByteArrayGenerator()),
    SHORT(List.of(Short.class, short.class), new ShortGenerator()),
    LONG(List.of(Long.class, long.class), new LongGenerator()),
    DOUBLE(List.of(Double.class, double.class), new DoubleGenerator()),
    FLOAT(List.of(Float.class, float.class), new FloatGenerator()),
    BOOLEAN(List.of(Boolean.class, boolean.class), new BooleanGenerator()),
    CHAR(List.of(Character.class, char.class), new CharGenerator()),
    INSTANT(List.of(Instant.class), new InstantGenerator()),
    DATE(List.of(Date.class), new DateGenerator()),
    LOCAL_DATE(List.of(LocalDate.class), new LocalDateGenerator()),
    MAP(List.of(Map.class), new MapGenerator()),
    LIST(List.of(List.class), new ListGenerator()),
    ENUM(List.of(), new EnumGenerator()),
    OBJECT(List.of(), new ObjectGenerator());

    final List<Type> values;
    final Generator generator;

    TypeEnum(List<Type> values, Generator generator) {
        this.values = values;
        this.generator = generator;
    }

    public static TypeEnum fromType(Type type) {
        Type rawType = type;
        if (type instanceof ParameterizedType) rawType = ((ParameterizedType) rawType).getRawType();
        Class<?> finalType = (Class<?>) rawType;
        TypeEnum typeEnum = Arrays.stream(TypeEnum.values())
                .filter(tEnum -> tEnum.values.contains(finalType))
                .findFirst()
                .orElseGet(() -> {
                    try {
                        if (finalType.getSuperclass().equals(AbstractMap.class))
                            return MAP;
                        if (finalType.getSuperclass().equals(AbstractList.class))
                            return LIST;
                        if (finalType.isEnum())
                            return ENUM;
                    } catch (Exception ignored) {
                    }
                    return OBJECT;
                });
        typeEnum.setType(type);
        return typeEnum;
    }

    public Generator generator() {
        return this.generator;
    }

    public void setType(Type type) {
        if (this.generator instanceof GenericTypeGenerator genericTypeGenerator) {
            genericTypeGenerator.setType((ParameterizedType) type);
        } else if (this.generator instanceof EnumGenerator enumGenerator) {
            enumGenerator.setType(type);
        } else if (this.generator instanceof ObjectGenerator objectGenerator) {
            objectGenerator.setType(type);
        }
    }
}
