import org.junit.jupiter.api.*;
import vetorlog.model.HelloWorld;
import vetorlog.model.util.relational.DatabaseManager;
import vetorlog.model.util.relational.JPAUtil;
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
        model.setValueString("Teste " + (int)(Math.random() * 1000));
        model.setValueDouble(Math.random() * 100000);
        dbManager.add(model);
    }

    @Test
    void deleteTest() {

    }

    @Test
    void updateTest() {
        HelloWorld model = dbManager.findById(HelloWorld.class, "360f6991-b689-4ded-a49b-d2901b8abfcf");
        model.setValueString("Modificado");
        model.setValueDouble(-1);
        dbManager.update(model);
    }

    @Test
    void queryTest() {

    }
}
