import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.Contract;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import static java.time.ZoneOffset.UTC;

/**
 * this will be responsible for generating random instance of a class
 * it will be using java reflection api to access setter methods of a class
 * setter methods = public methods that starts with "set"
 *
 * @author obaydah bouifadene
 * @see java.lang.reflect
 */
public class RandomGenerator {
    private final Random random = new Random();
    private final Instant MIN_INSTANT = Instant.ofEpochMilli(0);
    private final Instant MAX_INSTANT = LocalDate.of(2100, 12, 31).atStartOfDay(UTC).toInstant();

    /**
     * if type.getName() in {"int", "java.lang.Integer"}
     *
     * @return a random int between 0 and Integer.MAX_VALUE
     */
    private int getRandomInt() {
        return random.nextInt(0, Integer.MAX_VALUE);
    }

    /**
     * generate a random short
     *
     * @return a random short between 0 and Short.MAX_VALUE
     */
    private short getRandomShort() {
        return (short) random.nextInt(0, Short.MAX_VALUE + 1);
    }

    /**
     * generate a random long
     *
     * @return a random long between 0 and Long.MAX_VALUE
     */
    private long getRandomLong() {
        return random.nextLong(0, Long.MAX_VALUE);
    }

    /**
     * generate a random double
     * @return a double between 0 and Double.MAX_VALUE
     */
    private double getRandomDouble(){return random.nextDouble(0,Double.MAX_VALUE);}

    /**
     * generate a random float
     *
     * @return a random float between 0.0 and Float.MAX_VALUE
     */
    private float getRandomFloat() {
        return random.nextFloat(0, Float.MAX_VALUE + 1);
    }

    /**
     * generate a random boolean
     *
     * @return a random boolean : true or false
     */
    private boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    /**
     * generates a random character
     *
     * @return random character from [a-zA-Z]
     */
    private char getRandomChar() {
        return RandomStringUtils.randomAlphabetic(1).charAt(0);
    }


    /**
     * if type.getName() is "java.lang.String"
     *
     * @return a random string of length 10
     * @see org.apache.commons.lang3.RandomStringUtils
     */
    @NotNull
    private String getRandomString() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    /**
     * if type.getName() in {"[B", "[Ljava.lang.Byte"}
     *
     * @return a random byte array with size between 5 and 15
     */
    @NotNull
    private byte[] getBytes() {
        int size = random.nextInt(5, 15);
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * generates a random Instant between two instants
     *
     * @param startInclusive : starting Instant (included)
     * @param endExclusive   : ending Instant (not included)
     * @return random instant of type Instant
     * @see <a href="https://github.com/RKumsher/utils/blob/9f27615414acaafc9a24ae85537be666efd52fe7/src/main/java/com/github/rkumsher/date/RandomDateUtils.java">Source Code</a>
     */
    @org.jetbrains.annotations.NotNull
    @NotNull
    private Instant getRandomInstant(Instant startInclusive, Instant endExclusive) {
        return Instant.ofEpochMilli(random.nextLong(startInclusive.toEpochMilli(), endExclusive.toEpochMilli()));
    }

    /**
     * generates a random Instant between {@link RandomGenerator#MIN_INSTANT Instant.Min} and {@link RandomGenerator#MAX_INSTANT 2100}
     *
     * @return a random Instant
     */
    @org.jetbrains.annotations.NotNull
    @NotNull
    private Instant getRandomInstant() {
        return this.getRandomInstant(MIN_INSTANT, MAX_INSTANT);
    }

    /**
     * generates a random Date between {@link RandomGenerator#MIN_INSTANT Instant.Min} and {@link RandomGenerator#MAX_INSTANT 2100}
     *
     * @return a random Date
     */
    @Contract(" -> new")
    @org.jetbrains.annotations.NotNull
    @NotNull
    private Date getRandomDate() {
        return Date.from(this.getRandomInstant());
    }

    /**
     * generates a random LocalDate between {@link RandomGenerator#MIN_INSTANT Instant.Min} and {@link RandomGenerator#MAX_INSTANT 2100}
     *
     * @return a random LocalDate
     */
    @Contract(" -> new")
    @org.jetbrains.annotations.NotNull
    @NotNull
    private LocalDate getRandomLocalDate() {
        return this.getRandomInstant().atZone(UTC).toLocalDate();
    }

    /**
     * if type.getName() in {"byte", "java.lang.Byte"}
     *
     * @return a random byte between -128 and 127
     */
    private byte getByte() {
        byte[] bytes = new byte[1];
        random.nextBytes(bytes);
        return bytes[0];
    }

    /**
     * if type extends Enum<>
     *
     * @param enumClass the enum class : to be passed as EnumName.class for example
     * @return a random Enum of the given type or null if the enum is empty
     */
    private <T extends Enum<T>> T getRandomEnum(@NotNull Class<T> enumClass) {
        EnumSet<T> enumSet = EnumSet.allOf(enumClass);
        if (enumSet.isEmpty()) {
            return null;
        }
        return enumSet.stream().skip(new Random().nextInt(enumSet.size())).findFirst().orElse(null);
    }
    public <K,V> Map<K,V> getRandomMap(Field field){
        // TODO @hamza
        return null;
    }
    public <T> List<T> getRandomList(Field field){
        // TODO @ibrahim
        return null;
    }
    // other list / array etc methods @Ibrahim

    /**
     * the facade to the other generation methods
     * will be the base method for generating random values for all types of fields
     *
     * @param type the type of the field
     * @return a random value of the according type Object
     * TODO : refactor this method to accept Field field instead to Type type
     */
    public Object getRandomObject(Type type) {
        if (type == Integer.class || type == int.class) {
            return getRandomInt();
        } else if (type == String.class) {
            return getRandomString();
        } else if (type == Byte.class || type == byte.class) {
            return getByte();
        } else if (type == Byte[].class || type == byte[].class) {
            return getBytes();
        } else if (type == Short.class || type == short.class) {
            return getRandomShort();
        } else if (type == Long.class || type == long.class) {
            return getRandomLong();
        } else if (type == Double.class || type == double.class) {
            return getRandomDouble();
        } else if (type == Float.class || type == float.class) {
            return getRandomFloat();
        } else if (type == Boolean.class || type == boolean.class) {
            return getRandomBoolean();
        } else if (type == Character.class || type == char.class) {
            return getRandomChar();
        } else if (((Class<?>) type).isEnum()) {
            return getRandomEnum((Class<? extends Enum>) type);
        } else if (type == Instant.class) {
            return getRandomInstant();
        } else if (type == Date.class) {
            return getRandomDate();
        } else if (type == LocalDate.class) {
            return getRandomLocalDate();
        }
        return null;
    }
}
