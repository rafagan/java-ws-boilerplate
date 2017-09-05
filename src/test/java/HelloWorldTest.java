import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloWorldTest {
    private static final Logger logger = LogManager.getRootLogger();

    @Test
    void firstTest() {
        logger.trace("hahaha");
        System.out.println("Teste");
        Assertions.assertEquals(1 + 3, 2 + 2);
    }
}
