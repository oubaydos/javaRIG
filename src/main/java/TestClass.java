import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * this is the test class for which we will be trying to generate random objects
 * it is annotated with @Setter
 * @see lombok.Setter
 * @author obaydah bouifadene
 */
@NoArgsConstructor
@Setter
@ToString
public class TestClass {
    private final String a = "";
    private Integer b;
    private String alpha;
    private Short shortNumber;
    private float floatNumber;
    private long longNumber;
    private boolean booleanNumber;
    private Character character;
    private byte by;
    private byte[] bytes;
    private TestEnum testEnum;
//    private List<String> list;
}

enum TestEnum{
    A,B,C
}