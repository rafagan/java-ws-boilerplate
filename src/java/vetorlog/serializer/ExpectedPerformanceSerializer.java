package vetorlog.serializer;

import org.jvnet.hk2.annotations.Service;
import vetorlog.dto.ExpectedPerformanceDTO;
import vetorlog.manager.DatabaseManager;
import vetorlog.model.ExpectedPerformanceModel;
import vetorlog.model.TypologyPumpModel;
import vetorlog.model.UserModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpectedPerformanceSerializer {
    @Inject
    private DatabaseManager dbManager;

    public ExpectedPerformanceDTO fromModelToDTO(ExpectedPerformanceModel model) {
        ExpectedPerformanceDTO dto = new ExpectedPerformanceDTO();
        dto.setId(model.getId());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setMaximum(model.getMaximum());
        dto.setMedian(model.getMedian());
        dto.setMinimum(model.getMinimum());
        dto.setSatisfactory(model.getSatisfactory());
        dto.setUnsatisfactory(model.getUnsatisfactory());
        dto.setWithoutCredibility(model.getWithoutCredibility());
        dto.setUserName(model.getUser().getName());

        return dto;
    }

    public ExpectedPerformanceModel fromDTOToModel(ExpectedPerformanceDTO dto) {
        ExpectedPerformanceModel model = new ExpectedPerformanceModel();
        model.setCreatedAt(dto.getCreatedAt());
        model.setMaximum(dto.getMaximum());
        model.setMedian(dto.getMedian());
        model.setMinimum(dto.getMinimum());
        model.setSatisfactory(dto.getSatisfactory());
        model.setUnsatisfactory(dto.getUnsatisfactory());
        model.setWithoutCredibility(dto.getWithoutCredibility());

        if(dto.getTypologyPumpId() != null) {
            TypologyPumpModel t = dbManager.find(TypologyPumpModel.class, dto.getTypologyPumpId());
            model.setTypologyPump(t);
        }

        return model;
    }

    public List<ExpectedPerformanceDTO> fromModelListToDTOList(List<ExpectedPerformanceModel> models) {
        List<ExpectedPerformanceDTO> dtos = new ArrayList<>();
        for(ExpectedPerformanceModel model: models)
            dtos.add(fromModelToDTO(model));
        return dtos;
    }
}
