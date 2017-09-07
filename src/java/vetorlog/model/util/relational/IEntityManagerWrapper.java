package vetorlog.model.util.relational;

import javax.persistence.EntityManager;

public interface IEntityManagerWrapper {
    EntityManager getEntityManager();
    void setEntityManager(EntityManager entityManager);
}
