
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "mysql_local";
    private static EntityManagerFactory factory;

    static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        return factory;
    }

    static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
