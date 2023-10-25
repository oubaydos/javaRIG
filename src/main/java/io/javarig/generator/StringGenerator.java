package io.javarig.generator;

import com.mifmif.common.regex.Generex;
import io.javarig.RandomInstanceGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

@Setter
@Getter
public class StringGenerator extends TypeGenerator {
    public StringGenerator(Type type, RandomInstanceGenerator randomInstanceGenerator) {
        super(type, randomInstanceGenerator);
    }

    /**
     * if minLength < possible regex generation, it will be ignored
     * anchors (^, $) are not supported, and will be treated as ordinary characters
     */
    @Override
    public String generate() {
        String regex = getConfig().getRegexPattern();
        regex = StringUtils.strip(regex, "^");
        regex = StringUtils.strip(regex, "$");
        Generex generex = new Generex(regex, getRandom());
        return generex.random(getConfig().getMinSizeInclusive(), getConfig().getMaxSizeExclusive() - 1);
    }
}
