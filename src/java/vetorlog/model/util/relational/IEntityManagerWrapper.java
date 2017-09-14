package vetorlog.model.util.relational;

import org.jvnet.hk2.annotations.Contract;

import javax.persistence.EntityManager;

@Contract
public interface IEntityManagerWrapper {
    EntityManager getEntityManager();
    void setEntityManager(EntityManager entityManager);
}
