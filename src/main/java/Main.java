import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
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
//        Field testMap = Test.class.getDeclaredField("genericTestMap");
//        testMap.setAccessible(true);
//
//        ParameterizedType type = (ParameterizedType) testMap.getGenericType();
//        for (var i : Arrays.stream(TestClass.class.getDeclaredFields()).map(Field::getGenericType).toList()){
//            testClass.
//        }
        for (var method : Arrays.stream(TestClass.class.getDeclaredMethods()).filter(method -> method.getName().startsWith("set")).toList()) {
            try {

                Field testMap = TestClass.class.getDeclaredField(Utils.getFieldNameFromSetterMethodName(method.getName()));
                Type type =  testMap.getGenericType();
                log.debug("{}",type == Character.class);
                method.invoke(testClass,
                        randomGenerator.getRandomObject(type)
                );
//                break;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("{}", testClass);
//

    }

}
