package io.javarig.generator;

import com.mifmif.common.regex.Generex;
import io.javarig.RandomInstanceGenerator;
import lombok.Getter;
import lombok.Setter;
import java.lang.reflect.Type;

@Setter
@Getter
public class StringGenerator extends TypeGenerator {
    public StringGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    /**
     * if minLength < possible regex generation, it will be ignored
     * todo ignore ^ and $
     */
    @Override
    public String generate() {
        Generex generex = new Generex(getConfig().getRegexPattern(), getRandom());
        return generex.random(getConfig().getMinSizeInclusive(), getConfig().getMaxSizeExclusive() - 1);
    }
}
