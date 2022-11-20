package io.javarig.generator.list;

import java.util.ArrayList;
import java.util.List;

public class ArrayListGenerator extends ListGenerator {
    @Override
    @SuppressWarnings("rawtypes")
    protected Class<? extends List> getImplementationType() {
        return ArrayList.class;
    }
}
