import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.Contract;


import java.lang.reflect.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.time.ZoneOffset.UTC;

/**
 * this will be responsible for generating random instance of a class
 * it will be using java reflection api to access setter methods of a class
 * setter methods = public methods that starts with "set"
 *
 * @author obaydah bouifadene
 * @see java.lang.reflect
 */
@Slf4j
public class RandomGenerator {
    private final Random random = new Random();
    private final Instant MIN_INSTANT = Instant.ofEpochMilli(0);
    private final Instant MAX_INSTANT = LocalDate.of(2100, 12, 31).atStartOfDay(UTC).toInstant();

    //new add by bihi_23

    private final  Integer MIN_COLLECTION_SIZE = 5 ;
    private final  Integer MAX_COLLECTION_SIZE = 5 ;

    // bihi_23

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
     *
     * @return a double between 0 and Double.MAX_VALUE
     */
    private double getRandomDouble() {
        return random.nextDouble(0, Double.MAX_VALUE);
    }

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
    private byte[] getRandomBytes() {
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
    private byte getRandomByte() {
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

    /**
     * @return a random Map with size between 5 and 15
     */

    private Map<Object, Object> getRandomMap(Type type) {
        int size = random.nextInt(5, 15);
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type keyType = parameterizedType.getActualTypeArguments()[0];
        Type valueType = parameterizedType.getActualTypeArguments()[1];
        Map<Object, Object> resultedMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            resultedMap.put(generateRandomObjectForType(keyType), generateRandomObjectForType(valueType));
        }
        return resultedMap;
    }

    //added by bihi23



    public <T> List<T> getRandomList(List<T> listTofill) throws NoSuchFieldException {
        // TODO @ibrahim
        int randomSize = ThreadLocalRandom
                .current()
                .nextInt(MIN_COLLECTION_SIZE, MAX_COLLECTION_SIZE);

        Field ListField = RandomGenerator.class.getDeclaredField("listTofill");
        ParameterizedType ListType = (ParameterizedType) ListField.getGenericType();
        Class<?> inputListClass = (Class<?>) ListType.getActualTypeArguments()[0];
        //System.out.println(ListClass);

        List<Object> outputList = new ArrayList<Object>() ;

        for (int i = 0; i < randomSize ; i++) {
            outputList.add(getRandomObject(inputListClass)) ;
        }

        return (List<T>) outputList;

    }
    public <T> List<T> getRandomList(Type type) {
        int randomSize = ThreadLocalRandom
                .current()
                .nextInt(MIN_COLLECTION_SIZE, MAX_COLLECTION_SIZE);
        ParameterizedType parameterizedType = (ParameterizedType)type;
        Class<?> inputListClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];

        List<Object> outputList = new ArrayList<Object>() ;

        for (int i = 0; i < randomSize ; i++) {
            outputList.add(getRandomObject(inputListClass)) ;
        }

        return (List<T>) outputList;

    }

    // added by bihi23
    // other list / array etc methods @Ibrahim

    /**
     * the facade to the other generation methods
     * will be the base method for generating random values for all types of fields
     *
     * @param type the type of the field
     * @return a random value of the according type Object
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object generateRandomObjectForType(Type type) {
        TypeEnum typeEnum = TypeEnum.fromType(type);
        // TODO : nested objects are not yet supported
        // TODO : when supporting nested objects watch out for infinite recursion ( Set a maximum recursion depth for example )
        return switch (typeEnum) {
            case INTEGER -> getRandomInt();
            case STRING -> getRandomString();
            case BYTE -> getRandomByte();
            case BYTE_ARRAY -> getRandomBytes();
            case SHORT -> getRandomShort();
            case LONG -> getRandomLong();
            case DOUBLE -> getRandomDouble();
            case FLOAT -> getRandomFloat();
            case BOOLEAN -> getRandomBoolean();
            case CHAR -> getRandomChar();
            case INSTANT -> getRandomInstant();
            case DATE -> getRandomDate();
            case LOCAL_DATE -> getRandomLocalDate();
            case MAP -> getRandomMap(type);
            case LIST -> getRandomList(type);
            case ENUM -> getRandomEnum((Class<? extends Enum>) type);
            case UNDEFINED -> null;
        };
    }


    /**
     * generate a random object of type objectClass
     * @param objectClass the class of the object - not null
     * @param <T> the object type
     * @return a random object of type objectClass - not null
     * @throws NoSuchMethodException when the no arguments' constructor for the class does not exist.
     * @throws InvocationTargetException if the underlying constructor throws an exception
     * @throws InstantiationException if the class {@code objectClass} is abstract
     * @throws IllegalAccessException if the no arguments' Constructor object is enforcing Java language access control and the underlying constructor is inaccessible.
     * @see java.lang.reflect.Constructor#newInstance(java.lang.Object...) for the 3 exceptions above
     * @throws NoSuchFieldException if a field with the specified name is not found
     * @see java.lang.Class#getDeclaredField(String)
     */
    @NotNull
    public <T> T generateRandomObject(@NotNull Class<T> objectClass) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T object = objectClass.getConstructor().newInstance();
        log.info("created object of type {}", objectClass.getName());
        for (var method : Arrays.stream(objectClass.getDeclaredMethods()).filter(method -> method.getName().startsWith("set")).toList()) {
            Field field = TestClass.class.getDeclaredField(Utils.getFieldNameFromSetterMethodName(method.getName()));
            Type type = field.getGenericType();
            method.invoke(object, this.generateRandomObjectForType(type));
        }
        log.info("created object {}", object);
        return object;
    }
}
