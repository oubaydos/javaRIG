package io.javarig.generator;

import com.mifmif.common.regex.Generex;
import io.javarig.RandomInstanceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;

import static io.javarig.util.Utils.removeUnsupportedRegexCharacters;

@Setter
@Getter
public class StringGenerator extends TypeGenerator {
    public StringGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    /**
     * if minLength < possible regex generation, it will be ignored
     * anchors (^, $) and backslash (\) are not supported, and will be ignored
     */
    @Override
    public String generate() {
        String regex = removeUnsupportedRegexCharacters(getConfig().getRegexPattern());
        Generex generex = new Generex(regex, getRandom());
        return generex.random(getConfig().getMinSizeInclusive(), getConfig().getMaxSizeExclusive() - 1);
    }
}
