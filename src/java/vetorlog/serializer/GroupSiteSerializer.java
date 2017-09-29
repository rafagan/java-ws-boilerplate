package vetorlog.serializer;

import vetorlog.dto.GroupSiteDTO;
import vetorlog.model.GroupSitesModel;

import java.util.ArrayList;
import java.util.List;

public class GroupSiteSerializer {
    public static GroupSiteDTO fromModelToDTO(GroupSitesModel model) {
        GroupSiteDTO dto = new GroupSiteDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());

        return dto;
    }

    public static List<GroupSiteDTO> fromModelListToDTOList(List<GroupSitesModel> models) {
        List<GroupSiteDTO> dtos = new ArrayList<>();
        for(GroupSitesModel model: models)
            dtos.add(fromModelToDTO(model));
        return dtos;
    }
}
