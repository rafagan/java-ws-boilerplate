package vetorlog.model.adapter;

import lombok.NoArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import vetorlog.manager.DatabaseManager;
import vetorlog.model.TypologyPumpModel;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
@NoArgsConstructor
public class TypologyPumpAdapter extends DatabaseManager {
    public List<TypologyPumpModel> filterByGroupSiteId(long groupSiteId, int page, int size) {
        TypedQuery<TypologyPumpModel> query = getEntityManager().createQuery(
                "SELECT m FROM TypologyPumpModel AS m JOIN m.groupSites AS gs ON gs.id = :groupSiteId", TypologyPumpModel.class);
        query.setParameter("groupSiteId", groupSiteId);
        return find(query, page, size);
    }
}
