package io.javarig.generator;

public class ByteGenerator extends AbstractTypeGenerator {

    @Override
    public Byte generate() {
        byte[] bytes = new byte[1];
        getRandom().nextBytes(bytes);
        return bytes[0];
    }
}
