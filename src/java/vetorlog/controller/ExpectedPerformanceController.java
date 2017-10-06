package vetorlog.controller;

import org.jvnet.hk2.annotations.Service;
import vetorlog.controller.prototype.Controller;
import vetorlog.dto.ExpectedPerformanceDTO;
import vetorlog.model.ExpectedPerformanceModel;
import vetorlog.model.adapter.ExpectedPerformanceAdapter;
import vetorlog.serializer.ExpectedPerformanceSerializer;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class ExpectedPerformanceController extends Controller {
    @Inject
    private ExpectedPerformanceAdapter dbAdapter;

    @Inject
    private ExpectedPerformanceSerializer serializer;

    public Response get(long typologyPumpId, int page, int size) {
        List<ExpectedPerformanceModel> models = dbAdapter.filterByTypologyPumpId(typologyPumpId, page, size);
        List<ExpectedPerformanceDTO> data = serializer.fromModelListToDTOList(models);
        return response.ok(data);
    }

    public Response post(ExpectedPerformanceDTO dto) {
        ExpectedPerformanceModel model = serializer.fromDTOToModel(dto);
        model.setUser(user());
        dbAdapter.insert(model);
        return response.ok();
    }

    public Response put(long id, ExpectedPerformanceDTO dto) {
        dto.setId(id);
        ExpectedPerformanceModel model = serializer.fromDTOToModel(dto);
        model.setUser(user());
        dbAdapter.update(model);
        return response.ok();
    }

    public Response delete(long id) {
        dbAdapter.delete(ExpectedPerformanceModel.class, id);
        return response.ok();
    }
}
