package io.javarig.util;

import java.lang.reflect.Type;
import java.util.Map;

public class GenericTypes {
    public static Type resolve(Type type, Map<String, Type> genericTypesMap){
        if(genericTypesMap.containsKey(type.getTypeName())){
            return genericTypesMap.get(type.getTypeName());
        }
        return type;
    }
}
