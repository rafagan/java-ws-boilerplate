package vetorlog.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import vetorlog.conf.Constant;
import vetorlog.controller.TypologyPumpController;
import vetorlog.util.type.RoleType;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(
        value = "/typology_pumps",
        description = "Tipologia das bombas",
        authorizations = {@Authorization(value="apiKey")}
)
@Log4j2
@Path("v1/typology_pumps")
public class TypologyPumpAPI {
    @Inject
    private TypologyPumpController controller;

    @ApiOperation(
            value = "Listagem de tipologia das bombas",
            produces = MediaType.APPLICATION_JSON)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleType.ADMIN, RoleType.USER})
    public Response getExpectedPerformances(
            @QueryParam("group_site_id")
                    int groupSiteId,
            @QueryParam("page")
                    int page,
            @QueryParam("size")
            @Size(min = 1)
            @DefaultValue(Constant.DEFAULT_PAGE_SIZE)
                    int size
    ) {
        return controller.get(groupSiteId, page, size);
    }
}
