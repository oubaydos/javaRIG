package io.javarig.testclasses;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClassWithSomeNonPublicSetters {

    private Integer integerWithPrivateSetter;
    private Integer integerWithProtectedSetter;
    private Float floatWithDefaultAccessModifierSetter;
    private Double doubleWithPublicSetter;

    public ClassWithSomeNonPublicSetters() {
    }

    private void setIntegerWithPrivateSetter(Integer integerWithPrivateSetter) {
        this.integerWithPrivateSetter = integerWithPrivateSetter;
    }
    protected void setIntegerWithProtectedSetter(Integer integerWithProtectedSetter) {
        this.integerWithProtectedSetter = integerWithProtectedSetter;
    }
    void setFloatWithDefaultAccessModifierSetter(Float floatWithDefaultAccessModifierSetter) {
        this.floatWithDefaultAccessModifierSetter = floatWithDefaultAccessModifierSetter;
    }

    public void setDoubleWithPublicSetter(Double doubleWithPublicSetter) {
        this.doubleWithPublicSetter = doubleWithPublicSetter;
    }


}
