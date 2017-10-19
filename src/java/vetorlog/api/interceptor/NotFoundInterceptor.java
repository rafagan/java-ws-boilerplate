package vetorlog.api.interceptor;

import vetorlog.api.util.ResponseFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Interceptor de erro 404
 * No caso especial dos erros 404, redireciona a página para o HTML de erro
 */
@Provider
public class NotFoundInterceptor implements ExceptionMapper<NotFoundException> {
    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse currentResponse;

    @Inject
    private ResponseFactory response;

    public Response toResponse(NotFoundException exception) {
        try {
            currentResponse.sendRedirect("/errors/404.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.ok();
    }
}