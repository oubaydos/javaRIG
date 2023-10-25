package io.javarig.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class Utils {
    public static String getFieldNameFromSetterMethodName(String setterMethodName, String setterPrefix) {
        String fieldName = setterMethodName.replaceFirst(setterPrefix, "");
        return Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
    }

    public static Field getOwnOrInheritedFieldByName(Class<?> type, String fieldName) throws NoSuchFieldException {
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            Optional<Field> optionalField = Arrays.stream(c.getDeclaredFields()).filter(field -> field.getName().equals(fieldName)).findFirst();
            if (optionalField.isPresent()) return optionalField.get();
        }
        throw new NoSuchFieldException(fieldName);
    }

    // will be used later
    public static boolean isClassGeneric(Class<?> aClass) {
        return aClass != null && aClass.getTypeParameters().length > 0;
    }
    public static String removeUnsupportedRegexCharacters(String regexPattern){// if other chars are unsupported maybe convert to list and loop
        regexPattern = StringUtils.replace(regexPattern, "^","");
        regexPattern = StringUtils.replace(regexPattern, "$","");
        regexPattern = StringUtils.replace(regexPattern, "\\","");
        return regexPattern;
    }
}
