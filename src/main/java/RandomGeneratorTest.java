import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
    void getRandomList() {
        List<Integer> integerList=randomGenerator.getRandomList(Integer.class);

    }

    @Test
    void generateRandomObjectForType() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        for (var method : Arrays.stream(TestClass.class.getDeclaredMethods()).filter(method -> method.getName().startsWith("set")).toList()) {
            Field field = TestClass.class.getDeclaredField(Utils.getFieldNameFromSetterMethodName(method.getName()));
            Type type = field.getGenericType();
            method.invoke(testClass, randomGenerator.generateRandomObjectForType(type));
        }
    }

    @Test
    void generateRandomObject() throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Object object=randomGenerator.generateRandomObject(TestClass.class);
    }
}