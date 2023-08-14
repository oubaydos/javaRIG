package io.javarig.testclasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GenericTestClass<T> {
    private List<T> list;
}
