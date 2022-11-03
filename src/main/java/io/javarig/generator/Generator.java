package io.javarig.generator;

import java.lang.reflect.Type;
import java.util.Random;

public interface Generator {
    Random random = new Random();
    Object generate(Type type);
}
