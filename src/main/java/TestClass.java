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
    private TestClass2 testClass2;
}

enum TestEnum{
    A,B,C
}