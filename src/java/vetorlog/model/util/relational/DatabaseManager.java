package vetorlog.model.util.relational;


import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import vetorlog.conf.Constant;
import vetorlog.model.prototype.IModel;
import vetorlog.model.prototype.Model;
import vetorlog.model.prototype.ModelLong;
import vetorlog.util.types.PersistenceContextType;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Log4j2
public class DatabaseManager {
    @Inject
    private IEntityManagerWrapper emw;

    private <T> boolean hasValidId(T model) {
        if(model instanceof ModelLong)
            return (((ModelLong) model).getId()).intValue() != 0;
        return ((Model) model).getId() != null;
    }

    /**
     * Captura o manager do JPA para operações avançadas. É capturado de forma lazy, de acordo com o ambiente 
     * (ex: ambiente local de desenvolvimento).
     * @return Manager do JPA
     */
    public EntityManager getEntityManager() {
        if(emw == null) {
            if(Constant.DATABASE_CONTEXT == PersistenceContextType.JTA)
                log.warn("JTA persistence context has been set, but the entity manager is being created without " +
                        "dependency injection");

            switch (Constant.ENVIRONMENT) {
                case LOCAL:
                    emw = new WrapperLocal();
                    break;
                case STAGING:
                    emw = new WrapperStaging();
                    break;
                case PRODUCTION:
                    emw = new WrapperProduction();
                    break;
                default:
                    log.warn("Choosing persistence_unit=default. Please, set DatabaseManager.ENVIRONMENT with " +
                            "one of EnvironmentType enum values.");
                    emw = new WrapperDefault();
            }
        }

        if(emw.getEntityManager() == null) {
            log.warn(String.format("Wrapper of environment type %s has a null EntityManager, verify if it's being " +
                    "injected and configured properly. For now, using default.", Constant.ENVIRONMENT));
            emw = new WrapperDefault();
        }

        return emw.getEntityManager();
    }

    /**
     * Seta manager do JPA. Utilizar apenas em ambientes se, Java Transacition API (JTA)
     * (ex: Resource Local).
     * @param wrapper EntityManager injeado pelo ambiente (ex: ambiente de desenvolvimento local)
     */
    public void setEntityManager(IEntityManagerWrapper wrapper) {
        emw = wrapper;
    }

    /**
     * Relê objeto com valores do banco de dados
     * @param object Objeto a ser relido
     * @return Objeto relido
     */
    @Transactional
    public <T extends IModel> T reload(T object) {
        if(Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL)
            this.getEntityManager().getTransaction().begin();

        this.getEntityManager().refresh(object);

        if(Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL) {
            this.getEntityManager().getTransaction().commit();
            this.getEntityManager().close();
        }

        return object;
    }

    /**
     * Adiciona um objeto no banco de dados
     * @param object Objeto a ser adicionado
     * @return Objeto relido com valores do banco
     */
    @Transactional
    public <T extends IModel> T insert(T object) {
        if (this.hasValidId(object))
            return update(object);

        try {
            if (Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL)
                this.getEntityManager().getTransaction().begin();

            this.getEntityManager().persist(object);
            this.getEntityManager().flush();
            this.getEntityManager().refresh(object);

            if (Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL) {
                this.getEntityManager().getTransaction().commit();
                this.getEntityManager().close();
            }
        } catch(Exception e) {
            if (Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL)
                this.getEntityManager().getTransaction().rollback();
            throw e;
        }

        return object;
    }

    /**
     * Atualiza um objeto no banco de dados
     * @param object Objeto que deve ser atualizado
     * @return Objeto atualizado
     */
    @Transactional
    public <T extends IModel> T update(T object) {
        try {
            if(Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL)
                this.getEntityManager().getTransaction().begin();

            object = this.getEntityManager().merge(object);

            if(Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL) {
                this.getEntityManager().getTransaction().commit();
                this.getEntityManager().close();
            }

        } catch(Exception e) {
            if (Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL)
                this.getEntityManager().getTransaction().rollback();
            throw e;
        }

        return object;
    }

    /**
     * Deleta um objeto no banco de dados
     * @param object Objeto a ser deletado
     */
    @Transactional
    public <T extends IModel> void delete(T object) {
        try {
            if(Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL)
                this.getEntityManager().getTransaction().begin();

            T a = this.getEntityManager().merge(object);
            this.getEntityManager().remove(a);

            if(Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL) {
                this.getEntityManager().getTransaction().commit();
                this.getEntityManager().close();
            }
        } catch(Exception e) {
            if (Constant.DATABASE_CONTEXT == PersistenceContextType.RESOURCE_LOCAL)
                this.getEntityManager().getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Lista todos os objetos do mesmo tipo cadastrados no banco de dados
     * @param objectClass Classe JPA da tabela
     * @return Lista dos objetos cadastrados
     */
    @SuppressWarnings({"unchecked"})
    public <T extends IModel> List<T> all(Class<T> objectClass) {
        Query query = this.getEntityManager().createQuery(
                "SELECT t FROM " + objectClass.getName() + " AS t"
        );
        return query.getResultList();
    }

    /**
     * Realiza busca de um objeto no banco de dados pelo ID (UUID ou Inteiro)
     * @param objectClass Classe JPA da tabela
     * @param id O id do object a ser buscado
     * @return Objeto encontrado
     */
    public <T extends IModel, U> T find(Class<T> objectClass, U id) {
        try {
            return this.getEntityManager().find(objectClass, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Lista todos os objetos que tenham os IDs especificados no banco de dados
     * @param objectClass Classe JPA da tabela
     * @param ids Identificadores dos objetos a serem lidos (UUID ou Inteiro)
     * @return Lista dos objetos encontrados
     */
    @SuppressWarnings({"unchecked"})
    public <T extends IModel, U> List<T> find(Class<T> objectClass, List<U> ids) {
        if(ids.isEmpty()) return new ArrayList<>();
        
        Query query = this.getEntityManager().createQuery(
                "SELECT t FROM " + objectClass.getName() + " AS t WHERE t.id in :ids");
        query.setParameter("ids", ids);
        
        return query.getResultList();
    }

    /**
     * Realiza uma consulta elaborada com uma pesquisa em texto e seus parâmetros mapeados em um dicionário
     * @param queryString Texto da pesquisa
     * @param queryParameters Parâmetros da pesquisa
     * @return Lista dos objetos resultantes da consulta
     */
    @SuppressWarnings("unchecked")
    public <T extends IModel> List<T> find(String queryString, Map<String, Object> queryParameters) {
        Query query = this.getEntityManager().createQuery(queryString);

        if(queryParameters != null)
            for (Map.Entry<String, Object> entry : queryParameters.entrySet())
                query.setParameter(entry.getKey(), entry.getValue());

        return query.getResultList();
    }


    /**
     * Realiza uma consulta elaborada com uma pesquisa em texto e seus parâmetros mapeados em um dicionário, com
     * o resultado separado em páginas. Prefira o uso de TypedQuery para ter acesso a funcionalidades de syntax color
     * @param queryString Texto da pesquisa
     * @param queryParameters Parâmetros da pesquisa
     * @param page Página desejada, calculada de acordo com o tamanho
     * @param size Tamanho da página
     * @return Lista de objetos resultates da consulta
     */
    @SuppressWarnings("unchecked")
    public <T extends IModel> List<T> find(String queryString, Map<String, Object> queryParameters, int page, int size) {
        Query query = this.getEntityManager().createQuery(queryString);

        if(queryParameters != null)
            for (Map.Entry<String, Object> entry : queryParameters.entrySet())
                query.setParameter(entry.getKey(), entry.getValue());

        query.setMaxResults(size);
        query.setFirstResult(size * page);

        return query.getResultList();
    }

    /**
     * Realiza uma consulta elaborada com o resultado separado em páginas.
     * @param query Pesquisa, com parâmetros
     * @param page Página desejada, calculada de acordo com o tamanho
     * @param size Tamanho da página
     * @return Lista de objetos resultates da consulta
     */
    public <T extends IModel> List<T> find(TypedQuery<T> query, int page, int size) {
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Realiza uma consulta elaborada com o resultado separado em páginas.
     * @param query Pesquisa, com parâmetros
     * @param page Página desejada, calculada de acordo com o tamanho
     * @param size Tamanho da página
     * @return Lista de objetos resultates da consulta
     */
    public List find(Query query, int page, int size) {
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Conta os elementos de uma tabela
     * @param objectClass Classe JPA da tabela
     * @return Quantidade de elementos
     */
    public <T extends IModel> int count(Class<T> objectClass) {
        Query query = this.getEntityManager().createQuery("SELECT COUNT(*) FROM " + objectClass.getName());
        return Integer.valueOf(query.getSingleResult().toString());
    }

    /**
     * Retorna o primeiro resultado para a tabela
     * @param objectClass Classe JPA da tabela
     * @return Objeto do primeiro elemento encontrado
     */
    @SuppressWarnings("unchecked")
    public <T extends IModel> T first(Class<T> objectClass) {
        Query query = this.getEntityManager().createQuery("SELECT n FROM " + objectClass.getName() + " AS n");
        List<T> list = this.find(query, 0, 1);

        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * Retorna o primeiro resultado da pesquisa, utilizando texto e dicionário para os valores a serem setados
     * na pesquisa
     * @param queryString Texto da pesquisa
     * @param queryParameters Parâmetros da pesquisa
     * @return Objeto do primeiro elemento encontrado
     */
    @SuppressWarnings("unchecked")
    public <T extends IModel> T first(String queryString, Map<String, Object> queryParameters) {
        return (T) find(queryString, queryParameters).stream().findFirst().orElse(null);

    }

    /**
     * Retorna o primeiro resultado da query, utilizando TypedQuery
     * @return Objeto do primeiro elemento encontrado
     */
    public <T extends IModel> T first(TypedQuery<T> query) {
        return query.getResultList().stream().findFirst().orElse(null);
    }
}