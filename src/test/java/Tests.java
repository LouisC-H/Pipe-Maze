import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    void testA(){
        Assertions.assertEquals(4, Main.runDay10Code("src/main/resources/test1.txt"));
    }

    @Test
    void testB(){
        Assertions.assertEquals(8, Main.runDay10Code("src/main/resources/test2.txt"));
    }
}
