package vetorlog.api;

import vetorlog.api.util.ResponseFactory;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/swagger")
public class SwaggerAPI {
    @Context
    HttpServletResponse currentResponse;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response indexPage() throws IOException {
        currentResponse.sendRedirect("/swagger/dist/");
        return ResponseFactory.ok();
    }
}
