package vetorlog.api;

import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import vetorlog.conf.Constant;
import vetorlog.controller.ExampleController;
import vetorlog.util.type.RoleType;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Api(
    value = "/example",
    description = "Exemplos de API mais complexos",
    authorizations = {@Authorization(value="apiKey")}
)
@Log4j2
@Path("v1/example")
public class ExampleAPI {
    @Inject
    private ExampleController controller;

    @ApiOperation(value = "Requisição GET com query params e retorno de JSON", produces = MediaType.APPLICATION_JSON)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWithQueryParams(
            @QueryParam("interval") int interval,
            @QueryParam("page")
                    int page,
            @QueryParam("size")
            @Size(min = 1)
            @DefaultValue(Constant.DEFAULT_PAGE_SIZE)
                    int size)
    {
        // Utilizar para testar a captura de erros do Sentry
//        if(true)
//            throw new NullPointerException("adasknasdjnasdjk");

        return controller.get(interval, page, size);
    }

    @ApiOperation(
            value = "Requisição POST, com login e geração de token com dados do header",
            produces = MediaType.TEXT_PLAIN)
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String postToLogin() {
        String result = "Hello World POST";
        log.log(Level.INFO, result);
        return result;
    }

    @ApiOperation(value = "Requisição PUT, com atualização de dados via JSON", produces = MediaType.TEXT_PLAIN)
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({RoleType.ADMIN, RoleType.USER})
    public String putWithJson() {
        String result = "Hello World PUT";
        log.log(Level.INFO, result);
        return result;
    }

    @RolesAllowed({RoleType.USER})
    @ApiOperation(value = "Requisição DELETE, com deleção de arquivo por Path Param", produces = MediaType.TEXT_PLAIN)
    @DELETE
    public String deleteWithPathParam() {
        String result = "Hello World DELETE";
        log.log(Level.INFO, result);
        return result;
    }

    @DenyAll
    @ApiOperation(value = "Requisição PATCH, com atualização de alguns campos via JSON, com validação", produces = MediaType.TEXT_PLAIN)
    @PATCH
    @Produces(MediaType.TEXT_PLAIN)
    public String patchSomeFields() {
        String result = "Hello World PATCH";
        log.log(Level.INFO, result);
        return result;
    }
}

