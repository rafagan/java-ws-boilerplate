import org.junit.jupiter.api.*;
import vetorlog.model.HelloWorld;
import vetorlog.model.util.relational.DatabaseManager;
import vetorlog.model.util.relational.WrapperLocal;

import javax.persistence.EntityManager;

class HibernateResourceLocalTest {
    private EntityManager entityManager;
    private DatabaseManager dbManager;

    @BeforeEach
    void startTransaction() {
        entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        dbManager = new DatabaseManager();
        dbManager.setEntityManager(new WrapperLocal(entityManager)); //Insersão manual do EM
    }

    @AfterEach
    void stopTransaction() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @AfterAll
    static void closeFactory() {
        JPAUtil.shutdown();
    }

    @Test
    void insertTest() {
        HelloWorld model = new HelloWorld();
        model.setTest("Teste 123");
        model.setOtherTest(2);
        dbManager.add(model);
    }
}
