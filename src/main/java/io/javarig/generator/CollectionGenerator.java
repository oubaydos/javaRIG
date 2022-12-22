package io.javarig.generator;

/**
 * random generator for collections : lists, maps, arrays ...
 * aka the classes that accept a "size"
 */
public interface CollectionGenerator {
    int DEFAULT_MIN_SIZE_INCLUSIVE = 5;
    int DEFAULT_MAX_SIZE_EXCLUSIVE = 15;

    int getMaxSizeExclusive();

    int getMinSizeInclusive();

    void setMinSizeInclusive(int minSizeInclusive);

    void setMaxSizeExclusive(int maxSizeExclusive);

    default void setSize(int size) {
        this.setMinSizeInclusive(size);
        this.setMaxSizeExclusive(size + 1);
    }
}
