package io.javarig.generator;

public class CharGenerator extends AbstractTypeGenerator {
    @Override
    public Character generate() {
        return (char) getRandom().nextInt(Character.MAX_VALUE);
    }
}
