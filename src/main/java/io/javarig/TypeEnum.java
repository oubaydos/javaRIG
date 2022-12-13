package io.javarig;

import io.javarig.generator.*;
import io.javarig.generator.collection.list.ArrayListGenerator;
import io.javarig.generator.collection.map.HashMapGenerator;
import io.javarig.generator.collection.map.TreeMapGenerator;
import lombok.NonNull;

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
    final AbstractTypeGenerator generator;

    TypeEnum(List<Type> values, AbstractTypeGenerator generator) {
        this.values = values;
        this.generator = generator;
    }

    /**
     * gets a TypeEnum instance and prepare its generator
     *
     * @param type                    Type instance to get its associated TypeEnum
     * @param randomInstanceGenerator instance that will be used to generate nested objects
     * @return TypeEnum instance associated to the type object with generator prepared with necessary objects
     */
    public static TypeEnum getTypeEnum(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        Type rawType = getRawType(type);
        TypeEnum typeEnum = getTypeEnumFromType(rawType);
        prepareGenerator(type, randomInstanceGenerator, typeEnum);
        return typeEnum;
    }

    /**
     * prepare the generator with setting its randomInstanceGenerator and type fields
     *
     * @param type                    Type instance
     * @param randomInstanceGenerator RandomInstanceGenerator that will be used in generating nested objects
     * @param typeEnum                TypeEnum instance that we prepare its generator
     */
    private static void prepareGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator, TypeEnum typeEnum) {
        typeEnum.setType(type);
        typeEnum.setRandomInstanceGenerator(randomInstanceGenerator);
    }

    /**
     * gets the rawType if type is instance of parameterizedType, returns type otherwise
     *
     * @param type Type instance
     * @return the rawType of the type object
     */
    private static Type getRawType(Type type) {
        if (type instanceof ParameterizedType parameterizedType) {
            return parameterizedType.getRawType();
        }
        return type;
    }

    /**
     * gets the TypeEnum associated to the type object
     */
    private static TypeEnum getTypeEnumFromType(Type type) {
        return Arrays.stream(TypeEnum.values())
                .filter(tEnum -> tEnum.values.contains(type))
                .findFirst()
                .orElseGet(() -> getObjectIfNotEnum(type));
    }

    @NonNull
    private static TypeEnum getObjectIfNotEnum(Type type) {
        if (((Class<?>) type).isEnum())
            return ENUM;
        return OBJECT;
    }

    private void setRandomInstanceGenerator(RandomInstanceGenerator randomInstanceGenerator) {
        this.generator.setRandomInstanceGenerator(randomInstanceGenerator);
    }

    public TypeGenerator generator() {
        return this.generator;
    }

    public void setType(Type type) {
        if (this.generator instanceof TypeBasedGenerator typeBasedGenerator) {
            typeBasedGenerator.setType(type);
        }
    }
}
