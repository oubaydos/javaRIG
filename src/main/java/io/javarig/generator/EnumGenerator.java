package io.javarig.generator;

import java.lang.reflect.Type;
import java.util.EnumSet;
import java.util.Random;

public class EnumGenerator<T extends Enum<T>> implements Generator{

    private Class<T> tClass;

    @Override
    public T generate() {
        EnumSet<T> enumSet = EnumSet.allOf(tClass);
        if (enumSet.isEmpty()) {
            return null;
        }
        return enumSet.stream().skip(new Random().nextInt(enumSet.size())).findFirst().orElse(null);
    }


    public Class<T> getType() {
        return tClass;
    }

    public void setType(Type type) {
        tClass = (Class<T>) type;
    }
}
