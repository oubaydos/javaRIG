//package io.javarig;
//
//import io.javarig.exception.NestedObjectRecursionException;
//import io.javarig.util.Utils;
//import jakarta.validation.constraints.NotNull;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.jetbrains.annotations.Contract;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.util.*;
//
//import static java.time.ZoneOffset.UTC;
//
///**
// * this will be responsible for generating random instance of a class
// * it will be using java reflection api to access setter methods of a class
// * setter methods = public methods that starts with "set"
// *
// * @author obaydah bouifadene
// * @see java.lang.reflect
// */
//@Slf4j
//@Deprecated
//public class OldRandomGenerator {
//
//    //stack that holds the history of objects that needs to be generated so that it detects recursion
//    private final Stack<Type> objectStack = new Stack<>();
//    private final Random random = new Random();
//    private final Instant MIN_INSTANT = Instant.ofEpochMilli(0);
//    private final Instant MAX_INSTANT = LocalDate.of(2100, 12, 31).atStartOfDay(UTC).toInstant();
//    private Integer minCollectionSize = 5;
//    private Integer maxCollectionSize = 15;
//
//    private Integer stringSize = 10;
//
//    public OldRandomGenerator(Integer minCollectionSize, Integer maxCollectionSize) {
//        this.minCollectionSize = minCollectionSize;
//        this.maxCollectionSize = maxCollectionSize;
//    }
//
//    public OldRandomGenerator(Integer stringSize) {
//        this.stringSize = stringSize;
//    }
//
//    public OldRandomGenerator(Integer minCollectionSize, Integer maxCollectionSize, Integer stringSize) {
//        this.minCollectionSize = minCollectionSize;
//        this.maxCollectionSize = maxCollectionSize;
//        this.stringSize = stringSize;
//    }
//
//    public OldRandomGenerator() {
//    }
//
//    /**
//     * if type.getName() in {"int", "java.lang.Integer"}
//     *
//     * @return a random int between 0 and Integer.MAX_VALUE
//     */
//    private int getRandomInt() {
//        return random.nextInt(0, Integer.MAX_VALUE);
//    }
//
//    /**
//     * generate a random short
//     *
//     * @return a random short between 0 and Short.MAX_VALUE
//     */
//    private short getRandomShort() {
//        return (short) random.nextInt(0, Short.MAX_VALUE + 1);
//    }
//
//    /**
//     * generate a random long
//     *
//     * @return a random long between 0 and Long.MAX_VALUE
//     */
//    private long getRandomLong() {
//        return random.nextLong(0, Long.MAX_VALUE);
//    }
//
//    /**
//     * generate a random double
//     *
//     * @return a double between 0 and Double.MAX_VALUE
//     */
//    private double getRandomDouble() {
//        return random.nextDouble(0, Double.MAX_VALUE);
//    }
//
//    /**
//     * generate a random float
//     *
//     * @return a random float between 0.0 and Float.MAX_VALUE
//     */
//    private float getRandomFloat() {
//        return random.nextFloat(0, Float.MAX_VALUE + 1);
//    }
//
//    /**
//     * generate a random boolean
//     *
//     * @return a random boolean : true or false
//     */
//    private boolean getRandomBoolean() {
//        return random.nextBoolean();
//    }
//
//    /**
//     * generates a random character
//     *
//     * @return random character from [a-zA-Z]
//     */
//    private char getRandomChar() {
//        return RandomStringUtils.randomAlphabetic(1).charAt(0);
//    }
//
//
//    /**
//     * if type.getName() is "java.lang.String"
//     *
//     * @return a random string of length 10
//     * @see org.apache.commons.lang3.RandomStringUtils
//     */
//    @NotNull
//    private String getRandomString() {
//        return RandomStringUtils.randomAlphanumeric(stringSize);
//    }
//
//    /**
//     * if type.getName() in {"[B", "[Ljava.lang.Byte"}
//     *
//     * @return a random byte array with size between 5 and 15
//     */
//    @NotNull
//    private byte[] getRandomBytes() {
//        int size = random.nextInt(minCollectionSize, maxCollectionSize);
//        byte[] bytes = new byte[size];
//        random.nextBytes(bytes);
//        return bytes;
//    }
//
//    /**
//     * generates a random Instant between two instants
//     *
//     * @param startInclusive : starting Instant (included)
//     * @param endExclusive   : ending Instant (not included)
//     * @return random instant of type Instant
//     * @see <a href="https://github.com/RKumsher/utils/blob/9f27615414acaafc9a24ae85537be666efd52fe7/src/main/java/com/github/rkumsher/date/RandomDateUtils.java">Source Code</a>
//     */
//    @org.jetbrains.annotations.NotNull
//    @NotNull
//    private Instant getRandomInstant(Instant startInclusive, Instant endExclusive) {
//        return Instant.ofEpochMilli(random.nextLong(startInclusive.toEpochMilli(), endExclusive.toEpochMilli()));
//    }
//
//    /**
//     * generates a random Instant between {@link OldRandomGenerator#MIN_INSTANT Instant.Min} and {@link OldRandomGenerator#MAX_INSTANT 2100}
//     *
//     * @return a random Instant
//     */
//    @org.jetbrains.annotations.NotNull
//    @NotNull
//    private Instant getRandomInstant() {
//        return this.getRandomInstant(MIN_INSTANT, MAX_INSTANT);
//    }
//
//    /**
//     * generates a random Date between {@link OldRandomGenerator#MIN_INSTANT Instant.Min} and {@link OldRandomGenerator#MAX_INSTANT 2100}
//     *
//     * @return a random Date
//     */
//    @Contract(" -> new")
//    @org.jetbrains.annotations.NotNull
//    @NotNull
//    private Date getRandomDate() {
//        return Date.from(this.getRandomInstant());
//    }
//
//    /**
//     * generates a random LocalDate between {@link OldRandomGenerator#MIN_INSTANT Instant.Min} and {@link OldRandomGenerator#MAX_INSTANT 2100}
//     *
//     * @return a random LocalDate
//     */
//    @Contract(" -> new")
//    @org.jetbrains.annotations.NotNull
//    @NotNull
//    private LocalDate getRandomLocalDate() {
//        return this.getRandomInstant().atZone(UTC).toLocalDate();
//    }
//
//    /**
//     * if type.getName() in {"byte", "java.lang.Byte"}
//     *
//     * @return a random byte between -128 and 127
//     */
//    private byte getRandomByte() {
//        byte[] bytes = new byte[1];
//        random.nextBytes(bytes);
//        return bytes[0];
//    }
//
//    /**
//     * if type extends Enum<>
//     *
//     * @param enumClass the enum class : to be passed as 'EnumName.class' for example
//     * @return a random Enum of the given type or null if the enum is empty
//     */
//    private <T extends Enum<T>> T getRandomEnum(@NotNull Class<T> enumClass) {
//        EnumSet<T> enumSet = EnumSet.allOf(enumClass);
//        if (enumSet.isEmpty()) {
//            return null;
//        }
//        return enumSet.stream().skip(new Random().nextInt(enumSet.size())).findFirst().orElse(null);
//    }
//
//    /**
//     * @return a random Map with size between 5 and 15
//     */
//    private Map<Object, Object> getRandomMap(Type type) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//        int size = random.nextInt(minCollectionSize, maxCollectionSize);
//        ParameterizedType parameterizedType = (ParameterizedType) type;
//        Type keyType = parameterizedType.getActualTypeArguments()[0];
//        Type valueType = parameterizedType.getActualTypeArguments()[1];
//        Map<Object, Object> resultedMap = new HashMap<>();
//        for (int i = 0; i < size; i++) {
//            resultedMap.put(generate(Class.forName(keyType.getTypeName())),
//                    generate(Class.forName(valueType.getTypeName())));
//        }
//        return resultedMap;
//    }
//
//    private List<Object> getRandomList(Type type) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//        int randomSize = random.nextInt(minCollectionSize, maxCollectionSize);
//        ParameterizedType parameterizedType = (ParameterizedType) type;
//        Type inputListType = parameterizedType.getActualTypeArguments()[0];
//        List<Object> outputList = new ArrayList<>(randomSize);
//        for (int i = 0; i < randomSize; i++) {
//            outputList.add(generate(Class.forName(inputListType.getTypeName())));
//        }
//        return outputList;
//    }
//
//    /**
//     * the facade to the other generation methods
//     * will be the base method for generating random values for all types of fields
//     *
//     * @param type the Class of the field
//     * @return a random value of the according type Object
//     * @throws NestedObjectRecursionException when this object create a recursion in generation; this object depends on itself to be generated,
//     *                                        so it can't be generated
//     */
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    public Object generate(Type type) throws NoSuchFieldException, InvocationTargetException,
//            NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException,
//            NestedObjectRecursionException {
//        //check if type exists in objectStack, if so then object can't be generated because there is recursion
//        // in this object's fields (there a field that it's instantiation depends on a father object)
//        //so NestedObjectRecursion is thrown
//        if (!objectStack.isEmpty() && objectStack.contains(type)) {
//            throw new NestedObjectRecursionException(type);
//        }
//        objectStack.push(type);
//        TypeEnum typeEnum = TypeEnum.fromType(type);
//        // Object o = typeEnum.getGenerator().generate();
//        log.info(type.getTypeName());
//        Object generatedObject = switch (typeEnum) {
//            case INTEGER -> getRandomInt();
//            case STRING -> getRandomString();
//            case BYTE -> getRandomByte();
//            case BYTE_ARRAY -> getRandomBytes();
//            case SHORT -> getRandomShort();
//            case LONG -> getRandomLong();
//            case DOUBLE -> getRandomDouble();
//            case FLOAT -> getRandomFloat();
//            case BOOLEAN -> getRandomBoolean();
//            case CHAR -> getRandomChar();
//            case INSTANT -> getRandomInstant();
//            case DATE -> getRandomDate();
//            case LOCAL_DATE -> getRandomLocalDate();
//            case MAP -> getRandomMap(type);
//            case LIST -> getRandomList(type);
//            case ENUM -> getRandomEnum((Class<? extends Enum>) type);
//            case OBJECT -> generateRandomObject(Class.forName(type.getTypeName()));
//        };
//        objectStack.pop();
//        return generatedObject;
//    }
//
//
//    public <T> T generateAndCast(Type type) throws NoSuchFieldException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        return (T) generate(type);
//    }
//
//    /**
//     * generate a random object of type objectClass
//     *
//     * @param objectClass the class of the object - not null
//     * @param <T>         the object type
//     * @return a random object of type objectClass - not null
//     * @throws NoSuchMethodException     when the no arguments' constructor for the class does not exist.
//     * @throws InvocationTargetException if the underlying constructor throws an exception
//     * @throws InstantiationException    if the class {@code objectClass} is abstract
//     * @throws IllegalAccessException    if the no arguments' Constructor object is enforcing Java language access control and the underlying constructor is inaccessible.
//     * @throws NoSuchFieldException      if a field with the specified name is not found
//     * @see java.lang.reflect.Constructor#newInstance(java.lang.Object...) for the 3 exceptions above
//     * @see java.lang.Class#getDeclaredField(String)
//     */
//    @NotNull
//    public <T> T generateRandomObject(@NotNull Class<T> objectClass) throws NoSuchFieldException, NoSuchMethodException,
//            InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//
//        T object = objectClass.getConstructor().newInstance();
//        log.info("generating object of type {} ...", objectClass.getName());
//        for (var method : Arrays.stream(objectClass.getDeclaredMethods())
//                .filter(method -> method.getName().startsWith("set")).toList()) {
//            Field field = objectClass.getDeclaredField(Utils.getFieldNameFromSetterMethodName(method.getName()));
//            Type type = field.getGenericType();
//            method.invoke(object, this.generate(type));
//        }
//        log.info("created object {}", object);
//        return object;
//    }
//}
