package vetorlog.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import vetorlog.conf.Constant;
import vetorlog.controller.ExpectedPerformanceController;
import vetorlog.dto.ExpectedPerformanceDTO;
import vetorlog.util.type.RoleType;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(
        value = "/expected_performances",
        description = "Desempenho esperado",
        authorizations = {@Authorization(value="apiKey")}
)
@Log4j2
@Path("v1/expected_performances")
public class ExpectedPerformanceAPI {
    @Inject
    private ExpectedPerformanceController controller;

    @ApiOperation(
            value = "Listagem de desempenho esperado",
            produces = MediaType.APPLICATION_JSON)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleType.ADMIN, RoleType.USER})
    public Response getExpectedPerformances(
            @QueryParam("typology_pump_id")
                    int typologyPumpId,
            @QueryParam("page")
                    int page,
            @QueryParam("size")
            @Size(min = 1)
            @DefaultValue(Constant.DEFAULT_PAGE_SIZE)
                    int size
    ) {
        return controller.get(typologyPumpId, page, size);
    }

    @ApiOperation(
            value = "Criação de desempenho esperado",
            consumes = MediaType.APPLICATION_JSON
    )
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleType.ADMIN, RoleType.USER})
    public Response postExpectedPerformance(
            ExpectedPerformanceDTO dto
    ) {
        return controller.post(dto);
    }

    @Path("{id}")
    @ApiOperation(
            value = "Edição de desempenho esperado",
            consumes = MediaType.APPLICATION_JSON
    )
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({RoleType.ADMIN, RoleType.USER})
    public Response putExpectedPerformance(
            @PathParam("id") long id,
            ExpectedPerformanceDTO dto
    ) {
        return controller.put(id, dto);
    }
}
