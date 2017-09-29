package vetorlog.controller;

import org.jvnet.hk2.annotations.Service;
import vetorlog.controller.prototype.Controller;
import vetorlog.dto.TypologyPumpDTO;
import vetorlog.model.TypologyPumpModel;
import vetorlog.model.adapters.TypologyPumpAdapter;
import vetorlog.serializer.TypologyPumpSerializer;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class TypologyPumpController extends Controller {
    @Inject
    private TypologyPumpAdapter dbAdapter;

    @Inject
    private TypologyPumpSerializer serializer;

    public Response get(int groupSiteId, int page, int size) {
        List<TypologyPumpModel> models = dbAdapter.filterByGroupSiteId(groupSiteId, page, size);
        List<TypologyPumpDTO> data = serializer.fromModelListToDTOList(models);
        return response.ok(data);
    }
}
