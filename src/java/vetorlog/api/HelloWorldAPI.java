package vetorlog.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//@Api(value = "Hello World")
//@RequestScoped
//@Log4j2
@Path("hello")
public class HelloWorldAPI {
//    @ApiOperation(value = "testando",produces = MediaType.TEXT_PLAIN)
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "Hello World GET";
    }

//    @ApiOperation(value = "testando",produces = MediaType.TEXT_PLAIN)
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String put() {
        return "Hello World PUT";
    }

//    @ApiOperation(value = "testando",produces = MediaType.TEXT_PLAIN)
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String delete() {
        return "Hello World DELETE";
    }

//    @ApiOperation(value = "testando",produces = MediaType.TEXT_PLAIN)
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String post() {
        return "Hello World POST";
    }
}
