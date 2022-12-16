package io.javarig.testclasses;

import java.util.HashMap;

public class FakeMap<K,V> extends HashMap<K,V> {
    public FakeMap(int initialCapacity) {
        super(initialCapacity);
    }
}
