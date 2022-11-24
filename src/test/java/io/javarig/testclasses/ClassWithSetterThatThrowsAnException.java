package io.javarig.testclasses;

public class ClassWithSetterThatThrowsAnException {
    int a;
    public void setA(int a){
        throw new UnsupportedOperationException();
    }
}
