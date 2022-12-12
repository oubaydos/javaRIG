package io.javarig.generator.set;

import io.javarig.generator.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.Set;


@Getter
@Setter
public abstract class SetGenerator extends AbstractTypeGenerator implements GenericCollectionGenerator<Set>, TypeBasedGenerator {
    private final static int NUMBER_OF_GENERIC_PARAMS = 1;
    private int minSizeInclusive = DEFAULT_MIN_SIZE_INCLUSIVE;
    private int maxSizeExclusive = DEFAULT_MAX_SIZE_EXCLUSIVE;
    private Type type;

}
