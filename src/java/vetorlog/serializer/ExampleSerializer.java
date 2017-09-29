package vetorlog.serializer;

import org.jvnet.hk2.annotations.Service;
import vetorlog.dto.ExampleDTO;
import vetorlog.model.ExampleModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExampleSerializer {
    // TODO: Validar o DTO que vem
    // TODO: Mapear parâmetros em comum e tentar fazer reflection
    // TODO: Mapear parâmetros extras resultantes de queries e implementar

    public ExampleDTO fromModelToDTO(ExampleModel model) {
        ExampleDTO dto = new ExampleDTO();
        dto.setId(model.getId());
        dto.setUpdatedAt(model.getUpdatedAt());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setValueString(model.getValueString());
        dto.setValueDouble(model.getValueDouble());

        return dto;
    }

    public ExampleModel fromDTOToModel(ExampleDTO dto) {
        ExampleModel model = new ExampleModel();
        model.setId(dto.getId());
        model.setUpdatedAt(dto.getUpdatedAt());
        model.setCreatedAt(dto.getCreatedAt());
        model.setValueString(dto.getValueString());
        model.setValueDouble(dto.getValueDouble());

        return model;
    }

    public List<ExampleDTO> fromModelListToDTOList(List<ExampleModel> models) {
        List<ExampleDTO> dtos = new ArrayList<>();
        for(ExampleModel model: models)
            dtos.add(fromModelToDTO(model));
        return dtos;
    }

    public List<ExampleModel> fromDTOListToModelList(List<ExampleDTO> dtos) {
        List<ExampleModel> models = new ArrayList<>();
        for(ExampleDTO dto: dtos)
            models.add(fromDTOToModel(dto));
        return models;
    }
}
