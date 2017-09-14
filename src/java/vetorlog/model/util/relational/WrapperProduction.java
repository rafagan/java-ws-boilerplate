package vetorlog.model.util.relational;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jvnet.hk2.annotations.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class WrapperProduction implements IEntityManagerWrapper {
    @PersistenceContext(name="mysql_production")
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
