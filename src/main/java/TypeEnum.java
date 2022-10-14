import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public enum TypeEnum {

    INTEGER(List.of(Integer.class,int.class)),
    STRING(List.of(String.class)),
    BYTE(List.of(Byte.class,byte.class)),
    BYTE_ARRAY(List.of(Byte[].class,byte[].class)),
    SHORT(List.of(Short.class,short.class)),
    LONG(List.of(Long.class,long.class)),
    DOUBLE(List.of(Double.class,double.class)),
    FLOAT(List.of(Float.class,float.class)),
    BOOLEAN(List.of(Boolean.class,boolean.class)),
    CHAR(List.of(Character.class,char.class)),
    INSTANT(List.of(Instant.class)),
    DATE(List.of(Date.class)),
    LOCAL_DATE(List.of(LocalDate.class)),
    MAP(List.of(Map.class)),
    LIST(List.of(List.class)),
    ENUM(List.of()),
    OBJECT(List.of());

    final List<Type> values;

    TypeEnum(List<Type> values) {
        this.values = values;
    }

    public static TypeEnum fromType(Type type){
        if(type instanceof ParameterizedType) type=((ParameterizedType) type).getRawType();
        Type finalType = type;
        return Arrays.stream(TypeEnum.values())
                .filter(typeEnum -> typeEnum.values.contains(finalType))
                .findFirst()
                .orElseGet(()->{
                    try{
                        if (((Class<?>) finalType).isEnum())
                            return ENUM;
                    }catch (Exception ignored){}
                    return OBJECT;
                });
    }
}
