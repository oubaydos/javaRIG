import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


/**
 * this is our playground for testing our code
 */
@Slf4j
public class Main {

    private static final RandomGenerator randomGenerator = new RandomGenerator();

    public static void main(String[] args) throws IllegalAccessException {
        TestClass testClass = new TestClass();
        log.debug("{}",Arrays.stream(TestClass.class.getDeclaredFields()).toList());
//        TestClass.class.getDeclaredField("b").setAccessible(true);
//        TestClass.class.getDeclaredField("b").set(testClass, 1);
        log.debug("{}", testClass);

//        for (var i : Arrays.stream(TestClass.class.getDeclaredFields()).map(Field::getGenericType).toList()){
//            testClass.
//        }
        for (var method : Arrays.stream(TestClass.class.getDeclaredMethods()).filter(method -> method.getName().startsWith("set")).toList()) {
            try {
                Class<?> type = method.getParameterTypes()[0];
                log.debug("{}",type == Character.class);
                method.invoke(testClass, randomGenerator.getRandomObject(type));
//                break;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        log.info("{}", testClass);
//

    }

}
