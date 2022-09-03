public class Utils {
    public static String getFieldNameFromSetterMethodName(String setterMethodName){
        String fieldName = setterMethodName.replace("set","");
        fieldName = Character.toLowerCase(fieldName.charAt(0))+fieldName.substring(1);
        return fieldName;
    }
}
