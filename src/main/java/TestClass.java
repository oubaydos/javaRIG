import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * this is the test class for which we will be trying to generate random objects
 * it is annotated with @Setter
 * @see lombok.Setter
 * @author obaydah bouifadene
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TestClass {
//    private final String a = "";
//    private Integer b;
//    private String alpha;
//    private Short shortNumber;
//    private float floatNumber;
//    private long longNumber;
//    private boolean booleanNumber;
//    private double doubleNumber;
//    private Character character;
//    private byte by;
//    private byte[] bytes;
//    private TestEnum testEnum;
//    private Instant instant;
//    private Date date;
//    private LocalDate localDate;
//    private Map<Instant,TestEnum> map;
//    private TestEnum e;
    private List<String> list;
}

enum TestEnum{
    A,B,C
}