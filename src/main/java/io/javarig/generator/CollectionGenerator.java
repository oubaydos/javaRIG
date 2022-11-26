package io.javarig.generator;

/**
 * random generator for collections : lists, maps, arrays ...
 * aka the classes that accept a "size"
 */
public interface CollectionGenerator extends TypeGenerator {

    int getMaxSizeExclusive();

    int getMinSizeInclusive();

    void setMinSizeInclusive(int minSizeInclusive);

    void setMaxSizeExclusive(int maxSizeExclusive);

    default void setSize(int size) {
        this.setMinSizeInclusive(size);
        this.setMaxSizeExclusive(size + 1);
    }
}
