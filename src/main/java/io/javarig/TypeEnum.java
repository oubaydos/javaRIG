package io.javarig;

import io.javarig.generator.*;
import io.javarig.generator.list.ArrayListGenerator;
import io.javarig.generator.map.HashMapGenerator;
import io.javarig.generator.map.TreeMapGenerator;

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
    MAP(List.of(Map.class), new HashMapGenerator()),
    HASH_MAP(List.of(HashMap.class), new HashMapGenerator()),
    TREE_MAP(List.of(TreeMap.class), new TreeMapGenerator()),
    LIST(List.of(List.class), new ArrayListGenerator()),
    ARRAY_LIST(List.of(ArrayList.class), new ArrayListGenerator()),

    ENUM(List.of(), new EnumGenerator()),
    OBJECT(List.of(), new ObjectGenerator());

    final List<Type> values;
    final AbstractGenerator generator;

    TypeEnum(List<Type> values, AbstractGenerator generator) {
        this.values = values;
        this.generator = generator;
    }

    public static TypeEnum getTypeEnum(Type type, RandomGenerator randomGenerator) {
        Type rawType = type;
        if (type instanceof ParameterizedType parameterizedType) rawType = parameterizedType.getRawType();
        Class<?> finalType = (Class<?>) rawType;
        TypeEnum typeEnum = Arrays.stream(TypeEnum.values())
                .filter(tEnum -> tEnum.values.contains(finalType))
                .findFirst()
                .orElseGet(() -> {
                    if (finalType.isEnum())
                        return ENUM;
                    return OBJECT;
                });
        typeEnum.setType(type);
        typeEnum.setRandomGenerator(randomGenerator);
        return typeEnum;
    }

    private void setRandomGenerator(RandomGenerator randomGenerator) {
        this.generator.setRandomGenerator(randomGenerator);
    }

    public Generator generator() {
        return this.generator;
    }

    public void setType(Type type) {
        if (this.generator instanceof TypeBasedGenerator typeBasedGenerator) {
            typeBasedGenerator.setType(type);
        }
    }
}
