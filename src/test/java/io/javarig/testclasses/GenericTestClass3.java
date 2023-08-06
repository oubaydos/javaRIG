package io.javarig.testclasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GenericTestClass3<T,K> {
    private T t;
    private K k;
}
