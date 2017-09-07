package vetorlog.model.util.relational;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapperResourceLocal implements IEntityManagerWrapper {
    private static EntityManagerFactory factory;
    private EntityManager em;

    static void startFactory(String persistenceUnitName) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public static void closeFactory() {
        if (factory != null)
            factory.close();
        factory = null;
    }

    @Override
    public EntityManager getEntityManager() {
        if(factory == null)
            throw new NullPointerException(
                    "Initialize EntityManagerFactory using startFactory. " +
                    "WrapperResourceLocal needs to know the persistenceUnitName.");
        if(em == null)
            em = factory.createEntityManager();
        return em;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
}
