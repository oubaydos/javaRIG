package io.javarig.generator;

public interface CollectionGenerator extends Generator{
    default int getMaxSizeExclusive(){
        return 15;
    }

    default int getMinSizeInclusive(){
        return 5;
    }

    void setMinSizeInclusive(int minSizeInclusive);
    void setMaxSizeExclusive(int maxSizeExclusive);
    default void setSize(int size){
        this.setMinSizeInclusive(size);
        this.setMaxSizeExclusive(size+1);
    }
}
