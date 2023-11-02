package io.javarig;

import io.javarig.generator.*;
import io.javarig.generator.collection.list.ArrayListGenerator;
import io.javarig.generator.collection.set.HashSetGenerator;
import io.javarig.generator.collection.set.LinkedHashSetGenerator;
import io.javarig.generator.collection.set.TreeSetGenerator;
import io.javarig.generator.map.HashMapGenerator;
import io.javarig.generator.map.TreeMapGenerator;
import io.javarig.generator.primitive.*;
import io.javarig.generator.time.DateGenerator;
import io.javarig.generator.time.InstantGenerator;
import io.javarig.generator.time.LocalDateGenerator;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;


public enum TypeEnum {

    INTEGER(Integer.class, IntegerGenerator.class),
    STRING(String.class, StringGenerator.class),
    BYTE(Byte.class, ByteGenerator.class),
    SHORT(Short.class, ShortGenerator.class),
    LONG(Long.class, LongGenerator.class),
    DOUBLE(Double.class, DoubleGenerator.class),
    FLOAT(Float.class, FloatGenerator.class),
    BOOLEAN(Boolean.class, BooleanGenerator.class),
    CHAR(Character.class, CharGenerator.class),
    INSTANT(Instant.class, InstantGenerator.class),
    DATE(Date.class, DateGenerator.class),
    LOCAL_DATE(LocalDate.class, LocalDateGenerator.class),
    MAP(Map.class, HashMapGenerator.class),
    HASH_MAP(HashMap.class, HashMapGenerator.class),
    TREE_MAP(TreeMap.class, TreeMapGenerator.class),
    LIST(List.class, ArrayListGenerator.class),
    ARRAY_LIST(ArrayList.class, ArrayListGenerator.class),
    SET(Set.class, HashSetGenerator.class),
    HASH_SET(HashSet.class, HashSetGenerator.class),
    LINKED_HASH_SET(LinkedHashSet.class, LinkedHashSetGenerator.class),
    TREE_SET(TreeSet.class, TreeSetGenerator.class),
    ENUM(null, EnumGenerator.class),
    ARRAY(null, ArrayGenerator.class),
    OBJECT(null, ObjectGenerator.class);

    final Type type;
    final Class<? extends TypeGenerator> generatorClass;

    TypeEnum(Type type, Class<? extends TypeGenerator> generatorClass) {
        this.type = type;
        this.generatorClass = generatorClass;
    }
}
