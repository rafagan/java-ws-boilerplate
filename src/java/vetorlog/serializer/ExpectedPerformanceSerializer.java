package vetorlog.serializer;

import vetorlog.dto.ExpectedPerformanceDTO;
import vetorlog.model.ExpectedPerformanceModel;

import java.util.ArrayList;
import java.util.List;

public class ExpectedPerformanceSerializer {
    public static ExpectedPerformanceDTO fromModelToDTO(ExpectedPerformanceModel model) {
        ExpectedPerformanceDTO dto = new ExpectedPerformanceDTO();
        dto.setCreatedAt(model.getCreatedAt());
        dto.setMaximum(model.getMaximum());
        dto.setMedian(model.getMedian());
        dto.setMinimum(model.getMinimum());
        dto.setSatisfactory(model.getSatisfactory());
        dto.setUnsatisfactory(model.getUnsatisfactory());
        dto.setUserId(model.getUserId());
        dto.setWithoutCredibility(model.getWithoutCredibility());

        return dto;
    }

    public static List<ExpectedPerformanceDTO> fromModelListToDTOList(List<ExpectedPerformanceModel> models) {
        List<ExpectedPerformanceDTO> dtos = new ArrayList<>();
        for(ExpectedPerformanceModel model: models)
            dtos.add(fromModelToDTO(model));
        return dtos;
    }
}
