import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;


/**
 * this is our playground for testing our code
 */
@Slf4j
public class Main {

    private static final RandomGenerator randomGenerator = new RandomGenerator();

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
//        randomGenerator.generateRandomObject(TestClass.class);
        TestClass testClass;
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
