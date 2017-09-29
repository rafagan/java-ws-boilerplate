package vetorlog.serializer;

import vetorlog.dto.TypologyPumpDTO;
import vetorlog.model.TypologyPumpModel;

import java.util.ArrayList;
import java.util.List;

public class TypologyPumpSerializer {
    public static TypologyPumpDTO fromModelToDTO(TypologyPumpModel model) {
        TypologyPumpDTO dto = new TypologyPumpDTO();
        dto.setId(model.getId());
        dto.setDescription(model.getDescription());
        dto.setObservations(model.getObservations());

        return dto;
    }

    public static List<TypologyPumpDTO> fromModelListToDTOList(List<TypologyPumpModel> models) {
        List<TypologyPumpDTO> dtos = new ArrayList<>();
        for(TypologyPumpModel model: models)
            dtos.add(fromModelToDTO(model));
        return dtos;
    }
}
