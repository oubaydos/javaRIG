package io.javarig.testclasses;

public class ClassWithNoFieldAssociatedToSetter {
    String s;
    int a;

    public void setA(int a) {
        this.a = a;
    }

    public String getS() {
        return s;
    }

    public int getA() {
        return a;
    }

    public void setString(String s) {
        this.s = s;
    }
}
