package vetorlog.model.util.relational;

import org.jvnet.hk2.annotations.Contract;

import javax.persistence.EntityManager;

/**
 * Interface de injeção de dependência de EntityManagerWrapper
 * O EntityManagerWrapper é responsável por definir o persistence-unit correto
 * Toda interface injetável deve ser anotada com @Contract e configurada em JerseyCDIConf
 */
@Contract
public interface IEntityManagerWrapper {
    EntityManager getEntityManager();
    void setEntityManager(EntityManager entityManager);
}
