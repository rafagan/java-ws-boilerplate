package vetorlog.api;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import vetorlog.controller.ExampleController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log4j2
@Path("v1/example")
public class ExampleAPI {
    // TODO: Injetar dependência
    private ExampleController controller = new ExampleController();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return controller.get();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String delete() {
        String result = "Hello World DELETE";
        log.log(Level.INFO, result);
        return result;
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String put() {
        String result = "Hello World PUT";
        log.log(Level.INFO, result);
        return result;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String post() {
        String result = "Hello World POST";
        log.log(Level.INFO, result);
        return result;
    }

    @PATCH
    @Produces(MediaType.TEXT_PLAIN)
    public String patch() {
        String result = "Hello World PATCH";
        log.log(Level.INFO, result);
        return result;
    }
}
