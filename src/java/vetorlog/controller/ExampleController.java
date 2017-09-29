package vetorlog.controller;

import org.jvnet.hk2.annotations.Service;
import vetorlog.controller.prototype.Controller;
import vetorlog.dto.ExampleDTO;
import vetorlog.model.ExampleModel;
import vetorlog.model.adapters.ExampleAdapter;
import vetorlog.serializer.ExampleSerializer;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class ExampleController extends Controller {
    @Inject
    private ExampleAdapter dbAdapter;

    public Response get(int interval, int page, int size) {
        List<ExampleModel> models = dbAdapter.filterByInterval(interval, page, size);
        List<ExampleDTO> data = ExampleSerializer.fromModelListToDTOList(models);
        return response.ok(data);
    }
}
