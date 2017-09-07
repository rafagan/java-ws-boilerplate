package vetorlog.api.interceptor;

import lombok.extern.log4j.Log4j2;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Log4j2
public class RequestInterceptor implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        log.debug("RequestInterceptor called");
    }
}