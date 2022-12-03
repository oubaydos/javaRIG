package io.javarig.util;

public class Utils {
    public static String getFieldNameFromSetterMethodName(String setterMethodName, String setterPrefix){
        String fieldName = setterMethodName.replace(setterPrefix,"");
        return Character.toLowerCase(fieldName.charAt(0))+fieldName.substring(1);
    }
}
