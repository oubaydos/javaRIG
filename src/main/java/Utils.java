public class Utils {
    public static String getFieldNameFromSetterMethodName(String setterMethodName){
        String fieldName = setterMethodName.replace("set","");
        return Character.toLowerCase(fieldName.charAt(0))+fieldName.substring(1);
    }
}
