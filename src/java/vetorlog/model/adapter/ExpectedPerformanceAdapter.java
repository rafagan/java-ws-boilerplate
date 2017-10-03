package vetorlog.model.adapter;

import lombok.NoArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import vetorlog.manager.DatabaseManager;
import vetorlog.model.ExpectedPerformanceModel;

import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@NoArgsConstructor
public class ExpectedPerformanceAdapter extends DatabaseManager {
    public List<ExpectedPerformanceModel> filterByTypologyPumpId(long typologyPumpId, int page, int size) {
        TypedQuery<ExpectedPerformanceModel> query = getEntityManager().createQuery(
                "SELECT m FROM ExpectedPerformanceModel AS m WHERE m.typologyPump.id = :typologyPumpId", ExpectedPerformanceModel.class);
        query.setParameter("typologyPumpId", typologyPumpId);
        return find(query, page, size);
    }
}
