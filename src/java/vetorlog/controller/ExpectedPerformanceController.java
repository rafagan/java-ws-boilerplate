package vetorlog.controller;

import org.jvnet.hk2.annotations.Service;
import vetorlog.controller.prototype.Controller;
import vetorlog.dto.ExpectedPerformanceDTO;
import vetorlog.dto.GroupSiteDTO;
import vetorlog.model.ExpectedPerformanceModel;
import vetorlog.model.GroupSitesModel;
import vetorlog.model.adapters.ExpectedPerformanceAdapter;
import vetorlog.model.adapters.TypologyPumpAdapter;
import vetorlog.serializer.ExpectedPerformanceSerializer;
import vetorlog.serializer.GroupSiteSerializer;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class ExpectedPerformanceController extends Controller {
    @Inject
    private ExpectedPerformanceAdapter dbAdapter;

    public Response get(int typologyPumpId, int page, int size) {
        List<ExpectedPerformanceModel> models = dbAdapter.filterByTypologyPumpId(typologyPumpId, page, size);
        List<ExpectedPerformanceDTO> data = ExpectedPerformanceSerializer.fromModelListToDTOList(models);
        return response.ok(data);
    }

    public Response post(ExpectedPerformanceDTO dto) {
        return null;
    }

    public Response put(ExpectedPerformanceDTO dto) {
        return null;
    }
}
