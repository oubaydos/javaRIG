package io.javarig.generator;

import java.util.HashMap;

public class FakeMap<K,V> extends HashMap<K,V> {
    public FakeMap(int initialCapacity) {
        super(initialCapacity);
    }
}
