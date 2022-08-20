import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Type;

public class RandomGenerator {
    /**
     * if type.getName() in {"int", "java.lang.Integer"}
     */
    public int getRandomInt() {
        return (int) (Math.random() * 100);
    }

    public String getRandomString() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    public Object getRandomObject(Type type) {
        if (type == Integer.class || type == int.class) {
            return getRandomInt();
        } else if (type == String.class) {
            return getRandomString();
        }
        return null;
    }
}
