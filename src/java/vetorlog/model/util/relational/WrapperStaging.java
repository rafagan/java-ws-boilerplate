package vetorlog.model.util.relational;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapperStaging implements IEntityManagerWrapper {
    public static EntityManagerFactory factory;
    private EntityManager em;

    private static void startFactory() {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory("mysql_staging");
    }

    public static void closeFactory() {
        if (factory != null)
            factory.close();
        factory = null;
    }

    @Override
    public EntityManager getEntityManager() {
        if(factory == null)
            WrapperStaging.startFactory();
        if(em == null || !em.isOpen())
            em = factory.createEntityManager();
        return em;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
}
