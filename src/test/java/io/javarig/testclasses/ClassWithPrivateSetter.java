package io.javarig.testclasses;

import lombok.Getter;

@Getter
public class ClassWithPrivateSetter {

    Integer a;

    public ClassWithPrivateSetter() {
    }

    private void setA(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "ClassWithPrivateSetter{" +
               "a=" + a +
               '}';
    }

}
