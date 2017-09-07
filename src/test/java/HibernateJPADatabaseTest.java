import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vetorlog.model.HelloWorld;
import vetorlog.model.util.relational.DatabaseManager;

import java.util.Map;

class HibernateJPADatabaseTest {
    private static final Logger logger = LogManager.getRootLogger();

    @Inject
    private DatabaseManager dbManager;

    @Test
    void insertTest() {
        HelloWorld model = new HelloWorld();
        model.setTest("Teste 123");
        dbManager.add(model);
    }
}
