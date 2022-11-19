package io.javarig.generator;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ListGenerator implements CollectionGenerator, TypeBasedGenerator {
    private int minSizeInclusive = 5;
    private int maxSizeExclusive = 15;

    private Type type;

    @Override
    public List<Object> generate() {
        int randomSize = random.nextInt(getMinSizeInclusive(), getMaxSizeExclusive());
        ParameterizedType parameterizedType = (ParameterizedType) getType();
        Type inputListType = parameterizedType.getActualTypeArguments()[0];
        List<Object> outputList = new ArrayList<>(randomSize);
        for (int i = 0; i < randomSize; i++) {
            try {
                outputList.add(randomGenerator.generate(Class.forName(inputListType.getTypeName())));
            } catch (Exception ignore) {
            }
        }
        return outputList;
    }
}
