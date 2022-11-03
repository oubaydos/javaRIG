package io.javarig.generator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class CollectionGenerator implements Generator{
    protected int maxSize = 15;
    protected int minSize = 5;

    public void setSize(int size){
        this.minSize = size;
        this.maxSize = size;
    }
}
