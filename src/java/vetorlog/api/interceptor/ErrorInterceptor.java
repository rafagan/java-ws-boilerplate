package vetorlog.api.interceptor;

import lombok.extern.log4j.Log4j2;
import vetorlog.api.util.ResponseFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Log4j2
public class ErrorInterceptor implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        log.error(e);
        return ResponseFactory.internalServerError(e.getMessage());
    }
}
