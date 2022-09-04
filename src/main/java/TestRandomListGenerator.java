import java.util.ArrayList;
import java.util.List;

public class TestRandomListGenerator {
    public static void main(String[] args) throws NoSuchFieldException {
        RandomGenerator rd = new RandomGenerator() ;

        List<String> listOfString = new ArrayList<>(); // "it should be initialized like that to work"

        System.out.println(rd.getRandomList(listOfString));
    }
}
