package vetorlog.controller;

import org.jvnet.hk2.annotations.Service;
import vetorlog.controller.prototype.Controller;
import vetorlog.dto.GroupSiteDTO;
import vetorlog.model.GroupSitesModel;
import vetorlog.serializer.GroupSiteSerializer;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class GroupSiteController extends Controller {
    @Inject
    private GroupSiteSerializer serializer;

    public Response get(int page, int size) {
        List<GroupSitesModel> models = dbManager.all(GroupSitesModel.class, page, size);
        List<GroupSiteDTO> data = serializer.fromModelListToDTOList(models);
        return response.ok(data);
    }
}
