package vetorlog.model.util.relational;


import lombok.NoArgsConstructor;
import vetorlog.conf.AppEnvironment;
import vetorlog.model.prototype.Model;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class DatabaseManager {
    public static AppEnvironment ENVIRONMENT;
    private EntityManager em;

    private <T> boolean hasValidId(T id) {
        if(id instanceof Number)
            return ((Number) id).intValue() != 0;
        return id != null;
    }

    /**
     * Captura o manager do JPA para operações avançadas. É capturado de forma lazy, de acordo com o ambiente 
     * (ex: ambiente local de desenvolvimento).
     * @return Manager do JPA
     */
    public EntityManager getEntityManager() {
        if(em == null)
            em = new WrapperLocal().getEntityManager();
        return em;
    }

    /**
     * Seta manager do JPA. Utilizar apenas em ambientes se, Java Transacition API (JTA)
     * (ex: Resource Local).
     * @param wrapper EntityManager injeado pelo ambiente (ex: ambiente de desenvolvimento local)
     */
    public void setEntityManager(IEntityManagerWrapper wrapper) {
        em = wrapper.getEntityManager();
    }

    /**
     * Adiciona um objeto no banco de dados
     * @param object Objeto a ser adicionado
     * @return Objeto relido com valores do banco
     */
    public <T extends Model> T add(T object) {
        if (this.hasValidId(object))
            return update(object);
        this.getEntityManager().persist(object);
        this.getEntityManager().flush();
        return this.refresh(object);
    }

    /**
     * Relê objeto com valores do banco de dados
     * @param object Objeto a ser relido
     * @return Objeto relido
     */
    public <T extends Model> T refresh(T object) {
        this.getEntityManager().refresh(object);
        return object;
    }

    /**
     * Atualiza um objeto no banco de dados
     * @param object Objeto que deve ser atualizado
     * @return Objeto atualizado
     */
    public <T extends Model> T update(T object) {
        return this.getEntityManager().merge(object);
    }

    /**
     * Deleta um objeto no banco de dados
     * @param object Objeto a ser deletado
     */
    public <T extends Model> void remove(T object) {
        T a = this.getEntityManager().merge(object);
        this.getEntityManager().remove(a);
    }

    /**
     * Realiza busca de um objeto no banco de dados pelo ID (UUID ou Inteiro)
     * @param _class Classe da tabela do banco de dados
     * @param id O id do object a ser buscado
     * @return Objeto encontrado
     */
    public <T extends Model, U> T findById(Class<T> _class, U id) {
        try {
            return this.getEntityManager().find(_class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Lista todos os objetos do mesmo tipo cadastrados no banco de dados
     * @param tableName Nome da tabela. Utilize de preferência o mesmo nome da classe a fins de genericidade e automação
     * @return Lista dos objetos cadastrados
     */
    @SuppressWarnings({"unchecked"})
    public <T extends Model> List<T> getAll(String tableName) {
        return this.getEntityManager().createQuery(
                "SELECT t FROM " + tableName + " AS t"
        ).getResultList();
    }

    /**
     * Lista todos os objetos que tenham os IDs especificados no banco de dados
     * @param tableName Nome da tabela. Utilize de preferência o mesmo nome da classe a fins de genericidade e automação
     * @param ids Identificadores dos objetos a serem lidos
     * @return Lista dos objetos encontrados
     */
    @SuppressWarnings({"unchecked"})
    public <T extends Model, U> List<T> find(String tableName, List<U> ids) {
        if(ids.isEmpty()) return new ArrayList<>();
        
        Query query = this.getEntityManager().createQuery(
                "SELECT t FROM  " + tableName + " AS t WHERE t.id in :ids");
        query.setParameter("ids", ids);
        
        return query.getResultList();
    }

    /**
     * Realizar uma consulta elaborada com uma pesquisa em texto e seus parâmetros mapeados em um dicionário
     * @param queryString Texto da pesquisa
     * @param queryParameters Parâmetros da pesquisa
     * @return Lista dos objetos resultantes da consulta
     */
    @SuppressWarnings("unchecked")
    public <T extends Model> List<T> find(String queryString, Map<String, Object> queryParameters) {
        Query query = this.getEntityManager().createQuery(queryString);
        
        if(queryParameters != null)
            for (Map.Entry<String, Object> entry : queryParameters.entrySet())
                query.setParameter(entry.getKey(), entry.getValue());

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
        Query query = this.getEntityManager().createQuery(consulta);

        if(values != null)
            for (Map.Entry<String, Object> entry : values.entrySet())
                query.setParameter(entry.getKey(), entry.getValue());

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
     * Conta os elementos de uma tabela
     * @return Quantidade de elementos na tabela
     */
    public <T extends Model> int countRow(Class<T> _class) {
        return Integer.valueOf(this.getEntityManager().createQuery("SELECT COUNT(*) FROM " + _class.getName()).getSingleResult().toString());
    }

    /**
     * Retorna o primeiro resultado para a tabela
     * @param tableName Nome da tabela. Utilize de preferência o mesmo nome da classe a fins de genericidade e automação
     * @return Model do primeiro elemento encontrado
     */
    @SuppressWarnings("unchecked")
    public <T extends Model> T findFirst(String tableName) {
        List<T> list = this.getPaged(String.format("SELECT n FROM %s", tableName), null, 0, 1);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * Retorna o primeiro resultado da pesquisa, utilizando texto e dicionário para os valores a serem setados
     * na pesquisa
     * @param queryString Texto da pesquisa
     * @param queryParameters Parâmetros da pesquisa
     * @return Model do primeiro elemento encontrado
     */
    @SuppressWarnings("unchecked")
    public <T extends Model> T findFirst(String queryString, Map<String, Object> queryParameters) {
        return (T) getResultList(queryString, queryParameters).stream().findFirst().orElse(null);

    }

    /**
     * Retorna o primeiro resultado da query, utilizando TypedQuery
     * @return Model do primeiro elemento encontrado
     */
    public <T extends Model> T findFirst(TypedQuery<T> query) {
        return query.getResultList().stream().findFirst().orElse(null);
    }
}