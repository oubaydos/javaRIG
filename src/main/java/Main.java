import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * this is our playground for testing our code
 */
public class Main {

    private static TestClass testClass;
    private static RandomGenerator randomGenerator = new RandomGenerator();

    public static void main(String[] args) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        testClass = new TestClass();
        System.out.println(Arrays.stream(TestClass.class.getDeclaredFields()).toList());
//        TestClass.class.getDeclaredField("b").setAccessible(true);
//        TestClass.class.getDeclaredField("b").set(testClass, 1);
        System.out.println(testClass);

//        for (var i : Arrays.stream(TestClass.class.getDeclaredFields()).map(Field::getGenericType).toList()){
//            testClass.
//        }
        for (var method : Arrays.stream(TestClass.class.getDeclaredMethods()).filter(method -> method.getName().startsWith("set")).toList()) {
            try {
                Class<?> type = method.getParameterTypes()[0];
                System.out.println(type == Character.class);
                method.invoke(testClass, randomGenerator.getRandomObject(type));
                System.out.println(testClass);
//                break;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
//

    }

}
