package vetorlog.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import vetorlog.conf.Constant;
import vetorlog.controller.GroupSiteController;
import vetorlog.util.type.RoleType;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(
        value = "/group_sites",
        description = "Group sites",
        authorizations = {@Authorization(value="apiKey")}
)
@Log4j2
@Path("v1/group_sites")
public class GroupSiteAPI {
    @Inject
    private GroupSiteController controller;

    @ApiOperation(
            value = "Listagem de group sites",
            produces = MediaType.APPLICATION_JSON)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleType.ADMIN, RoleType.USER})
    public Response getGroupSites(
            @QueryParam("page")
                    int page,
            @QueryParam("size")
            @Size(min = 1)
            @DefaultValue(Constant.DEFAULT_PAGE_SIZE)
                    int size
    ) {
        return controller.get(page, size);
    }
}
