package vetorlog.api.util;

import org.jvnet.hk2.annotations.Service;
import vetorlog.dto.error.DetailedErrorDTO;
import vetorlog.dto.error.UnauthorizedErrorDTO;
import vetorlog.dto.error.ErrorDTO;
import vetorlog.util.type.CustomErrorType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Respostas às requisições
 * Customize os retornos possíveis nos switches do header Accept: switch (accept)
 */
@Service
public class ResponseFactory {
    @Context
    private HttpServletRequest request;

    public Response ok() {
        return Response.ok().build();
    }

    public Response ok(Object dto) {
        return Response.ok(dto).build();
    }

    public Response noContent() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    public Response accepted(Object dto) {
        return Response.status(Response.Status.ACCEPTED).entity(dto).build();
    }

    public Response unauthorized(String message, boolean critical) {
        String accept = request.getHeaders("Accept").nextElement();

        switch (accept) {
            case MediaType.APPLICATION_JSON:
                return Response.status(Response.Status.UNAUTHORIZED).entity(new UnauthorizedErrorDTO(message, critical)).build();
            case MediaType.TEXT_PLAIN:
            default:
                return Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
        }
    }

    public Response notFound(String message) {
        return errorMessageResponse(Response.Status.NOT_FOUND, message);
    }

    public Response badRequest(String message) {
        return errorMessageResponse(Response.Status.BAD_REQUEST, message);
    }

    public Response badRequest(String message, CustomErrorType customError, Object details) {
        return errorMessageResponseDetailed(Response.Status.BAD_REQUEST, customError, message, details);
    }

    public Response internalServerError(String message) {
        return errorMessageResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
    }

    public Response forbidden(String message) {
        return errorMessageResponse(Response.Status.FORBIDDEN, message);
    }

    private Response errorMessageResponse(Response.StatusType status, String message) {
        String accept = request.getHeaders("Accept").nextElement();

        switch (accept) {
            case MediaType.APPLICATION_JSON:
                return Response.status(status).entity(new ErrorDTO(message)).build();
            case MediaType.TEXT_PLAIN:
            default:
                return Response.status(status).entity(message).build();
        }
    }

    private Response errorMessageResponseDetailed(Response.StatusType status,
                                                         CustomErrorType customError,
                                                         String message,
                                                         Object details)
    {
        String accept = request.getHeaders("Accept").nextElement();

        switch (accept) {
            case MediaType.APPLICATION_JSON:
                return Response.status(status).entity(
                        new DetailedErrorDTO(message, customError.toString(), details.toString())).build();
            case MediaType.TEXT_PLAIN:
            default:
                return Response.status(status).entity(message).build();
        }
    }
}