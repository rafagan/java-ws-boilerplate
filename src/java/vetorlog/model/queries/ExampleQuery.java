package vetorlog.model.queries;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import vetorlog.manager.DatabaseManager;
import vetorlog.model.ExampleModel;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ExampleQuery {
    @Inject
    private DatabaseManager dbManager;

    public List<ExampleModel> filterByInterval(int interval, int page, int size) {
        TypedQuery<ExampleModel> query = dbManager.getEntityManager().createQuery(
                "SELECT m FROM ExampleModel AS m WHERE m.valueDouble < :interval", ExampleModel.class);
        query.setParameter("interval", (double)interval);
        return dbManager.find(query, page, size);
    }
}
