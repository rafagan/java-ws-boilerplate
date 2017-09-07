package vetorlog.model.util.relational;


import lombok.NoArgsConstructor;
import vetorlog.conf.AppEnvironment;
import vetorlog.model.prototype.Model;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class DatabaseManager {
    public static AppEnvironment ENVIRONMENT;
    private EntityManager em;

    public EntityManager getEntityManager() {
        if(em == null)
            em = new WrapperLocal().getEntityManager();
        return em;
    }

    public void setEntityManager(IEntityManagerWrapper wrapper) {
        em = wrapper.getEntityManager();
    }

    private boolean hasValidId(Object id) {
        if(id instanceof Number)
            return ((Number) id).intValue() != 0;
        return id != null;
    }

    /**
     * Método responsável por adicionar um objeto no banco de dados
     *
     * @author Guizion Labs
     * @param item objeto a ser adicionado no banco de dados
     * @return objeto atualizado
     */
    public <T extends Model> T add(T item) {
        if (this.hasValidId(item))
            return update(item);
        this.em.persist(item);
        this.em.flush();
        return this.refresh(item);
    }

    public <T extends Model> T refresh(T item) {
        this.em.refresh(item);
        return item;
    }

    /**
     * Método responsável por atualizar um objeto no banco de dados
     *
     * @author Guizion Labs
     * @param item que deve ser atualizado no banco
     * @return objeto atualizado
     */
    public <T extends Model> T update(T item) {
        return this.em.merge(item);
    }

    /**
     * Método responsável por deletar um objeto no banco de dados
     *
     * @author Guizion Labs
     * @param item objeto a ser deletado no banco
     */
    public <T extends Model> void remove(T item) {
        T a = this.em.merge(item);
        this.em.remove(a);
    }

    /**
     * Método para realizar buscar um objeto no banco de dados pelo ID
     *
     * @author Guizion Labs
     * @param id o id do item a ser buscado
     * @return objeto armazenado no BD
     */
    public <T extends Model> T getById(Class<T> _class, Object id) {
        try {
            return this.em.find(_class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Método responsável por listar todos os objetos do mesmo tipo cadastrados
     * no banco de dados
     *
     * @author Guizion Labs
     * @return lista dos objetos cadastrados no banco
     */
    @SuppressWarnings({"unchecked"})
    public <T extends Model> List<T> getAll(Class<T> _class) {
        return this.em.createQuery("SELECT t FROM " + _class.getName() + " AS t").getResultList();
    }

    /**
     * Método responsável por listar todos os objetos que tenham os ids especificados
     * no banco de dados
     *
     * @author Guizion Labs
     * @return lista dos objetos cadastrados no banco
     */
    @SuppressWarnings({"unchecked"})
    public <T extends Model> List<T> getAllFromIds(Class<T> _class, List<Long> ids) {
        if(ids.isEmpty()) return new ArrayList<>();
        Query query = this.em.createQuery("SELECT t FROM  " + _class.getName() + " AS t WHERE t.id in :ids");
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    /**
     * Método para realizar uma consulta elaborada no banco de dados
     *
     * @author Guizion Labs
     * @param queryStr a consulta em HSQLDB
     * @param values valores a serem adicionados na consulta
     * @return lista de objetos resultantes da consulta
     */
    @SuppressWarnings("unchecked")
    public <T extends Model> List<T> getResultList(String queryStr, Map<String, Object> values) {
        Query query = this.em.createQuery(queryStr);

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }


    /**
     * Método para listar os objetos em páginas
     *
     * @author Guizion Labs
     * @param start index para começar a listagem
     * @param size tamanho da página
     * @return Lista de objetos resultates da consulta
     */
    @SuppressWarnings("unchecked")
    public <T extends Model> List<T> getPaged(String consulta, Map<String, Object> values, int start, int size) {
        Query query = this.em.createQuery(consulta);

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        query.setMaxResults(size);
        query.setFirstResult(start);

        return query.getResultList();
    }

    /**
     * Método para listar os objetos em páginas
     *
     * @author Guizion Labs
     * @param start index para começar a listagem
     * @param size tamanho da página
     * @return Lista de objetos resultates da consulta
     */
    public <T extends Model> List<T> getPaged(TypedQuery<T> query, int start, int size) {
        query.setFirstResult(start);
        query.setMaxResults(size);

        return query.getResultList();
    }
    public List getPaged(Query query, int start, int size) {
        query.setFirstResult(start);
        query.setMaxResults(size);

        return query.getResultList();
    }

    /**
     * Método para contar os elementos de uma tabela
     *
     * @author Guizion Labs
     * @return quantidade de elementos na tabela
     */
    public <T extends Model> int countRow(Class<T> _class) {
        return Integer.valueOf(this.em.createQuery("SELECT COUNT(*) FROM " + _class.getName()).getSingleResult().toString());
    }

    @SuppressWarnings("unchecked")
    public <T extends Model> T getSingleResult(String queryStr, Map<String, Object> values){
        return (T) getResultList(queryStr, values).stream().findFirst().orElse(null);

    }

    public <T extends Model> T getSingleResult(TypedQuery<T> query) {
        return query.getResultList().stream().findFirst().orElse(null);
    }
}