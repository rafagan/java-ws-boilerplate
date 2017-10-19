package vetorlog.model.adapter;

import lombok.NoArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import vetorlog.manager.DatabaseManager;
import vetorlog.model.ExampleModel;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Exemplo de pesquisa no banco de dados
 * Cada adapter adiciona ao DatabaseManager as pesquisas relacionadas ao Model em questão
 */
@Service
@NoArgsConstructor
public class ExampleAdapter extends DatabaseManager {
    public List<ExampleModel> filterByInterval(int interval, int page, int size) {
        TypedQuery<ExampleModel> query = getEntityManager().createQuery(
                "SELECT m FROM ExampleModel AS m WHERE m.valueDouble < :interval", ExampleModel.class);
        query.setParameter("interval", (double)interval);
        return find(query, page, size);
    }
}
