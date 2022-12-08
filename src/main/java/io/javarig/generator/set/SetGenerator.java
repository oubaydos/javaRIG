package io.javarig.generator.set;

import io.javarig.generator.AbstractTypeGenerator;
import io.javarig.generator.CollectionGenerator;
import io.javarig.generator.GenericTypeGenerator;
import io.javarig.generator.TypeBasedGenerator;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;


@Getter
@Setter
public abstract class SetGenerator extends AbstractTypeGenerator implements CollectionGenerator, TypeBasedGenerator, GenericTypeGenerator {
    private final static int NUMBER_OF_GENERIC_PARAMS = 2;
    private int minSizeInclusive = 5;
    private int maxSizeExclusive = 15;
    private Type type;

}
