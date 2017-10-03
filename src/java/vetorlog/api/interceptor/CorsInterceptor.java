package vetorlog.api.interceptor;


import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static com.google.common.net.HttpHeaders.*;

@SuppressWarnings("ALL")
@Provider
@Priority(1)
public class CorsInterceptor implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequest,
                       ContainerResponseContext containerResponse) throws IOException
    {
        final int ACCESS_CONTROL_MAX_AGE_IN_SECONDS = 12 * 60 * 60;
        MultivaluedMap<String, Object> headers = containerResponse.getHeaders();

        headers.add(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
        headers.add(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        headers.add(ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,PUT,DELETE,OPTIONS,HEAD");
        headers.add(ACCESS_CONTROL_MAX_AGE, ACCESS_CONTROL_MAX_AGE_IN_SECONDS);
    }
}