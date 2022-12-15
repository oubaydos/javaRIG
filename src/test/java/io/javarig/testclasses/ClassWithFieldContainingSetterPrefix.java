package io.javarig.testclasses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@ToString
@Getter
public class ClassWithFieldContainingSetterPrefix {
    private List<String> settersList;
}