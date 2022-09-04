import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// we will start by local variables
@Target(ElementType.METHOD)
public @interface RandomInstanceGenerator {
}
