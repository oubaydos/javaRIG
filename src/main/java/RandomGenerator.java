import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Type;

/**
 * this will be responsible for generating random instance of a class
 * it will be using java reflection api to access setter methods of a class
 * setter methods = public methods that starts with "set"
 * @see java.lang.reflect
 * @author obaydah bouifadene
 */
public class RandomGenerator {
    /**
     * if type.getName() in {"int", "java.lang.Integer"}
     * @return a random int between 0 and 100
     */
    public int getRandomInt() {
        return (int) (Math.random() * 100);
    }

    /**
     * if type.getName() is "java.lang.String"
     * @return a random string of length 10
     * @see org.apache.commons.lang3.RandomStringUtils
     */
    public String getRandomString() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    /**
     * will be the base method for generating random values for all types of fields
     * @param type the type of the field
     * @return a random value of the according type Object
     */
    public Object getRandomObject(Type type) {
        if (type == Integer.class || type == int.class) {
            return getRandomInt();
        } else if (type == String.class) {
            return getRandomString();
        }
        return null;
    }
}
