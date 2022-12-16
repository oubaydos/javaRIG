package io.javarig.testclasses;

import java.util.ArrayList;

public class  FakeList<E> extends ArrayList<E> {
    public FakeList(int initialCapacity) {
        super(initialCapacity);
    }
}
