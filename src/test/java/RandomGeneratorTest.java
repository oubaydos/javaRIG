import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class RandomGeneratorTest {
    RandomGenerator randomGenerator;
    TestClass testClass;

    @BeforeEach
    void setUp() {
        randomGenerator=new RandomGenerator();
        testClass = new TestClass();
    }

    @Test
    void generateRandomObjectForType() throws  InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Object object;
        for (var method : Arrays.stream(TestClass.class.getDeclaredMethods()).filter(method -> method.getName().startsWith("set")).toList()) {
            Field field = TestClass.class.getDeclaredField(Utils.getFieldNameFromSetterMethodName(method.getName()));
            Type type = field.getGenericType();
            object= randomGenerator.generateRandomObjectForType(type);
//            System.out.println(object);
            assertTrue(Utils.checkObjectHasValidateTypeAndNotEmpty(type,object));
//            System.out.println(object.getClass());
            //            method.invoke(testClass, randomGenerator.generateRandomObjectForType(type));
        }
    }

    @Test
    void generateRandomObject() throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestClass testClass1=randomGenerator.generateRandomObject(TestClass.class);

    }
}