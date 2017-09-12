package vetorlog.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Api(value = "/hello", description = "Hello World Jersey2/JaxRS e Swagger, com os principais verbos HTTP")
@Log4j2
@Path("v1/hello")
public class HelloWorldAPI {
    @ApiOperation(value = "Requisição GET", produces = MediaType.TEXT_PLAIN)
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        String result = "Hello World GET";
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

    @ApiOperation(value = "Requisição PUT", produces = MediaType.TEXT_PLAIN)
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String put() {
        String result = "Hello World PUT";
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

    @ApiOperation(value = "Requisição PATCH", produces = MediaType.TEXT_PLAIN)
    @PATCH
    @Produces(MediaType.TEXT_PLAIN)
    public String patch() {
        String result = "Hello World PATCH";
        log.log(Level.INFO, result);
        return result;
    }
}
