package io.javarig.generator;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;

@Getter
@Setter
public class EnumGenerator extends AbstractGenerator implements TypeBasedGenerator {
    private Type type;

    @Override
    public Object generate() {
        Object[] enumConstants = ((Class<?>) type).getEnumConstants();
        if (enumConstants.length == 0) {
            return null;
        }
        return enumConstants[getRandom().nextInt(enumConstants.length)];
    }
}
