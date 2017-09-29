package vetorlog.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import vetorlog.conf.Constant;
import vetorlog.controller.ExpectedPerformanceController;
import vetorlog.dto.ExpectedPerformanceDTO;

import javax.annotation.security.PermitAll;
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
    @PermitAll
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
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response postExpectedPerformance(
            @ApiParam ExpectedPerformanceDTO dto
    ) {
        return controller.post(dto);
    }

    @ApiOperation(
            value = "Edição de desempenho esperado",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response putExpectedPerformance(
            @ApiParam ExpectedPerformanceDTO dto
    ) {
        return controller.put(dto);
    }
}
