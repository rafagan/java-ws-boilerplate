package vetorlog.model.queries;

import vetorlog.model.ExampleModel;
import vetorlog.model.util.relational.DatabaseManager;

import javax.persistence.TypedQuery;
import java.util.List;

public class ExampleQuery {
    //Injetar
    private DatabaseManager dbManager = new DatabaseManager();

    public List<ExampleModel> filterByInterval(int interval, int page, int size) {
        TypedQuery<ExampleModel> query = dbManager.getEntityManager().createQuery(
                "SELECT m FROM ExampleModel AS m WHERE m.valueDouble < :interval", ExampleModel.class);
        query.setParameter("interval", (double)interval);
        return dbManager.find(query, page, size);
    }
}
