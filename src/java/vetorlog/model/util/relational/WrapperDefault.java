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
public class WrapperDefault implements IEntityManagerWrapper {
    private static EntityManagerFactory factory;
    private EntityManager em;

    private static void startFactory() {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory("default");
    }

    public static void closeFactory() {
        if (factory != null)
            factory.close();
        factory = null;
    }

    @Override
    public EntityManager getEntityManager() {
        if(factory == null)
            WrapperDefault.startFactory();
        if(em == null)
            em = factory.createEntityManager();
        return em;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
}
