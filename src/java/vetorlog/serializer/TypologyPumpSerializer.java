package vetorlog.serializer;

import org.jvnet.hk2.annotations.Service;
import vetorlog.dto.TypologyPumpDTO;
import vetorlog.model.TypologyPumpModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypologyPumpSerializer {
    public TypologyPumpDTO fromModelToDTO(TypologyPumpModel model) {
        TypologyPumpDTO dto = new TypologyPumpDTO();
        dto.setId(model.getId());
        dto.setDescription(model.getDescription());
        dto.setObservations(model.getObservations());

        return dto;
    }

    public List<TypologyPumpDTO> fromModelListToDTOList(List<TypologyPumpModel> models) {
        List<TypologyPumpDTO> dtos = new ArrayList<>();
        for(TypologyPumpModel model: models)
            dtos.add(fromModelToDTO(model));
        return dtos;
    }
}
