package vetorlog.model.util.relational;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapperLocal implements IEntityManagerWrapper {
//    @PersistenceContext(name="mysql_local")
    @PersistenceUnit(name="mysql_local")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        em = entityManager;
    }
}
