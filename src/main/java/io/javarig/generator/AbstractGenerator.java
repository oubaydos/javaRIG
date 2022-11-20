package io.javarig.generator;

import io.javarig.RandomGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter
public abstract class AbstractGenerator implements Generator {
    private Random random = new Random();
    private RandomGenerator randomGenerator;
}
