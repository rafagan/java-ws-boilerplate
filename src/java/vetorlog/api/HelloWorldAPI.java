package vetorlog.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import vetorlog.util.type.RoleType;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

@Api(value = "/hello", description = "Hello World Jersey2/JaxRS e Swagger, com os principais verbos HTTP")
@Log4j2
@Path("v1/hello")
public class HelloWorldAPI {
    private final String PRODUCES = MediaType.APPLICATION_JSON + ", " + MediaType.TEXT_PLAIN;

    @ApiOperation(value = "Requisição GET", produces = PRODUCES)
    @GET
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public String get() {
        String result = "Hello World GET";
        log.log(Level.INFO, result);
        return result;
    }

    @ApiOperation(value = "Requisição POST", produces = MediaType.TEXT_PLAIN)
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String post() {
        String result = "Hello World POST";
        log.log(Level.INFO, result);
        return result;
    }

    @ApiOperation(value = "Requisição PUT", produces = PRODUCES)
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String put() {
        String result = "Hello World PUT";
        log.log(Level.INFO, result);
        return result;
    }

    @ApiOperation(value = "Requisição DELETE", produces = MediaType.TEXT_PLAIN)
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String delete() {
        String result = "Hello World DELETE";
        log.log(Level.INFO, result);
        return result;
    }

    @ApiOperation(value = "Requisição PATCH", produces = MediaType.TEXT_PLAIN)
    @PATCH
    @Produces(MediaType.TEXT_PLAIN)
    public String patch() {
        String result = "Hello World PATCH";
        log.log(Level.INFO, result);
        return result;
    }
}
