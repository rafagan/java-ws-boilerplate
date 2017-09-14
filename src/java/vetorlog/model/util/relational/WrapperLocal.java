package vetorlog.model.util.relational;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jvnet.hk2.annotations.Service;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class WrapperLocal implements IEntityManagerWrapper {
    public static EntityManagerFactory factory;
    private EntityManager em;

    private static void startFactory() {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory("mysql_local");
    }

    public static void closeFactory() {
        if (factory != null)
            factory.close();
        factory = null;
    }

    @Override
    public EntityManager getEntityManager() {
        if(factory == null)
            WrapperLocal.startFactory();
        if(em == null || !em.isOpen())
            em = factory.createEntityManager();
        return em;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
}
