package io.javarig.testclasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GenericTestClass2<T,K> {
    private GenericTestClass3<K,T> genericClass3;
}
