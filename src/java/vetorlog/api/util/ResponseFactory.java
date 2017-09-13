package vetorlog.api.util;

import vetorlog.dto.error.DetailedError;
import vetorlog.dto.error.Unauthorized;
import vetorlog.dto.error.Error;
import vetorlog.util.types.CustomErrorType;

import javax.ws.rs.core.Response;

public class ResponseFactory {
    public static Response ok() {
        return Response.ok().build();
    }

    public static Response ok(Object dto) {
        return Response.ok(dto).build();
    }

    public static Response noContent() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    public static Response accepted(Object dto) {
        return Response.status(Response.Status.ACCEPTED).entity(dto).build();
    }

    public static Response unauthorized(String message, boolean critical) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(new Unauthorized(message, critical)).build();
    }

    public static Response notFound(String message) {
        return errorMessageResponse(Response.Status.NOT_FOUND, message);
    }

    public static Response badRequest(String message) {
        return errorMessageResponse(Response.Status.BAD_REQUEST, message);
    }

    public static Response badRequest(String message, CustomErrorType customError, Object details) {
        return errorMessageResponseDetailed(Response.Status.BAD_REQUEST, customError, message, details);
    }

    public static Response internalServerError(String message) {
        return errorMessageResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
    }

    public static Response forbidden(String message) {
        return errorMessageResponse(Response.Status.FORBIDDEN, message);
    }

    private static Response errorMessageResponse(Response.StatusType status, String message) {
        return Response.status(status).entity(new Error(message)).build();
    }

    private static Response errorMessageResponseDetailed(Response.StatusType status,
                                                         CustomErrorType customError,
                                                         String message,
                                                         Object details)
    {
        return Response.status(status).entity(
                new DetailedError(message, customError.toString(), details.toString())).build();
    }
}