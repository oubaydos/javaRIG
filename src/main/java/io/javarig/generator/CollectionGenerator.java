package io.javarig.generator;

public interface CollectionGenerator extends Generator{
    default int getMaxSize(){
        return 15;
    }

    default int getMinSize(){
        return 5;
    }

    void setMinSize(int minSize);
    void setMaxSize(int maxSize);
    default void setSize(int size){
        this.setMinSize(size);
        this.setMaxSize(size);
    }
}
