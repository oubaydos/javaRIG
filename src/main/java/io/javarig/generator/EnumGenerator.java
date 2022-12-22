package io.javarig.generator;

import io.javarig.RandomInstanceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;

@Getter
@Setter
public class EnumGenerator extends TypeGenerator {

    public EnumGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    @Override
    public Object generate() {
        Object[] enumConstants = ((Class<?>) getType()).getEnumConstants();
        if (enumConstants.length == 0) {
            return null;
        }
        return enumConstants[getRandom().nextInt(0, enumConstants.length)];
    }
}
