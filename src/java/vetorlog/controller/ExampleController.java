package vetorlog.controller;

import vetorlog.api.util.ResponseFactory;
import vetorlog.dto.ExampleDTO;
import vetorlog.model.ExampleModel;
import vetorlog.model.util.relational.DatabaseManager;
import vetorlog.serializer.ExampleSerializer;

import javax.ws.rs.core.Response;
import java.util.List;

public class ExampleController {
    // TODO: Injetar
    private DatabaseManager dbManager = new DatabaseManager();
    private ExampleSerializer serializer = new ExampleSerializer();

    public Response get() {
        List<ExampleModel> models = dbManager.all(ExampleModel.class);
        List<ExampleDTO> data = serializer.fromModelListToDTOList(models);
        return ResponseFactory.ok(data);
    }
}
