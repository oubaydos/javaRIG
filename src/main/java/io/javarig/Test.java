package io.javarig;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class Test {
    public static void main(String[] args) throws NoSuchFieldException {
        TestClass testClass = new TestClass();
        Field testMap = TestClass.class.getDeclaredField("map");
        log.info(testMap.getGenericType().getTypeName());
    }
}
