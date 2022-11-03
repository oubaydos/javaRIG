package io.javarig.generator;

import java.lang.reflect.Type;

public class ByteGenerator implements Generator {

    @Override
    public Byte generate(Type type) {
        byte[] bytes = new byte[1];
        random.nextBytes(bytes);
        return bytes[0];
    }
}
