import java.lang.reflect.Type;


public class Utils {
    public static String getFieldNameFromSetterMethodName(String setterMethodName){
        String fieldName = setterMethodName.replace("set","");
        return Character.toLowerCase(fieldName.charAt(0))+fieldName.substring(1);
    }
    public static boolean checkObjectHasValidateTypeAndNotEmpty(Type type,Object object){
        TypeEnum typeEnum = TypeEnum.fromType(type);
        return switch (typeEnum) {
            case INTEGER -> (Integer) object <Integer.MAX_VALUE;
            case STRING -> !((String) object).isEmpty();
            case BYTE -> true;
            case BYTE_ARRAY -> true;
            case SHORT -> (Short) object <Short.MAX_VALUE;
            case LONG -> (Long) object <Long.MAX_VALUE;
            case DOUBLE -> (Double) object <Double.MAX_VALUE;
            case FLOAT -> (Float) object <Float.MAX_VALUE;
            case BOOLEAN -> !((Boolean) object).describeConstable().isEmpty();
            case CHAR -> true;
            case INSTANT -> true;
            case DATE -> true;
            case LOCAL_DATE -> true;
            case MAP -> true;
            case LIST -> true;
            case ENUM -> true;
            case UNDEFINED -> false;
        };
    }
}
