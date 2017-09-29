package vetorlog.serializer;

import org.jvnet.hk2.annotations.Service;
import vetorlog.dto.GroupSiteDTO;
import vetorlog.model.GroupSitesModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupSiteSerializer {
    public GroupSiteDTO fromModelToDTO(GroupSitesModel model) {
        GroupSiteDTO dto = new GroupSiteDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());

        return dto;
    }

    public List<GroupSiteDTO> fromModelListToDTOList(List<GroupSitesModel> models) {
        List<GroupSiteDTO> dtos = new ArrayList<>();
        for(GroupSitesModel model: models)
            dtos.add(fromModelToDTO(model));
        return dtos;
    }
}
