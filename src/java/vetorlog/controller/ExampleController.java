package vetorlog.controller;

import vetorlog.api.util.ResponseFactory;
import vetorlog.dto.ExampleDTO;
import vetorlog.model.ExampleModel;
import vetorlog.model.queries.ExampleQuery;
import vetorlog.model.util.relational.DatabaseManager;
import vetorlog.serializer.ExampleSerializer;

import javax.ws.rs.core.Response;
import java.util.List;

public class ExampleController {
    // TODO: Injetar
    private DatabaseManager dbManager = new DatabaseManager();
    private ExampleQuery query = new ExampleQuery();

    public Response get(int interval, int page, int size) {
        List<ExampleModel> models = query.filterByInterval(interval, page, size);
        List<ExampleDTO> data = ExampleSerializer.fromModelListToDTOList(models);
        return ResponseFactory.ok(data);
    }
}
