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

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException, ClassNotFoundException {
        randomGenerator.generateRandomObject(TestClass.class);
//        TestClass.class.getDeclaredField("b").setAccessible(true);
//        TestClass.class.getDeclaredField("b").set(testClass, 1);
//        Field testMap = Test.class.getDeclaredField("genericTestMap");
//        testMap.setAccessible(true);
//
//        ParameterizedType type = (ParameterizedType) testMap.getGenericType();
//        for (var i : Arrays.stream(TestClass.class.getDeclaredFields()).map(Field::getGenericType).toList()){
//            testClass.
//        }

    }

}
