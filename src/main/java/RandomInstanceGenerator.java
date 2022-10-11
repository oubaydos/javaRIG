import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
// we will start by Class, for example we ll be adding a method public static CurrentClass random();
@Target(ElementType.TYPE)
public @interface RandomInstanceGenerator {
}
