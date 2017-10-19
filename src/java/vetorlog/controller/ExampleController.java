package vetorlog.controller;

import org.jvnet.hk2.annotations.Service;
import vetorlog.controller.prototype.Controller;
import vetorlog.dto.ExampleDTO;
import vetorlog.model.ExampleModel;
import vetorlog.model.adapter.ExampleAdapter;
import vetorlog.serializer.ExampleSerializer;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Exemplo de Controller
 * Camada responsável por receber os dados da requisição devidamente desserializados, realizar as modificações no banco de dados
 * e devolver o resultado apropriado à requisição. Aqui, deve-se enxergar claramente as regras de negócio do projeto
 */
@Service
public class ExampleController extends Controller {
    @Inject
    private ExampleAdapter dbAdapter;

    @Inject
    private ExampleSerializer serializer;

    public Response get(int interval, int page, int size) {
        List<ExampleModel> models = dbAdapter.filterByInterval(interval, page, size);
        List<ExampleDTO> data = serializer.fromModelListToDTOList(models);
        return response.ok(data);
    }
}
