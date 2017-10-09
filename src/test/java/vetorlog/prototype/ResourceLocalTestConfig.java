package vetorlog.prototype;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import vetorlog.manager.DatabaseManager;
import vetorlog.model.util.relational.WrapperDefault;

public class ResourceLocalTestConfig {
    protected DatabaseManager dbManager;

    @BeforeEach
    void startTransaction() {
//        dbManager = new DatabaseManager();
//        dbManager.setEntityManager(new WrapperDefault());
//        dbManager.getEntityManager().getTransaction().begin();
    }

    @AfterEach
    void stopTransaction() {
//        dbManager.getEntityManager().getTransaction().commit();
        dbManager.getEntityManager().close();
    }

    @AfterAll
    static void closeFactory() {
        WrapperDefault.closeFactory();
    }
}
