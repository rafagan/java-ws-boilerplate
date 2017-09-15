package vetorlog.controller;

import org.jvnet.hk2.annotations.Service;
import vetorlog.controller.prototype.Controller;
import vetorlog.dto.ExampleDTO;
import vetorlog.model.ExampleModel;
import vetorlog.model.queries.ExampleQuery;
import vetorlog.serializer.ExampleSerializer;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class ExampleController extends Controller {
    @Inject
    private ExampleQuery query;

    public Response get(int interval, int page, int size) {
        List<ExampleModel> models = query.filterByInterval(interval, page, size);
        List<ExampleDTO> data = ExampleSerializer.fromModelListToDTOList(models);
        return response.ok(data);
    }
}
