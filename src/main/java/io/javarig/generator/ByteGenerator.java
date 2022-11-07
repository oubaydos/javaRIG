package io.javarig.generator;

public class ByteGenerator implements Generator {

    @Override
    public Byte generate() {
        byte[] bytes = new byte[1];
        random.nextBytes(bytes);
        return bytes[0];
    }
}
