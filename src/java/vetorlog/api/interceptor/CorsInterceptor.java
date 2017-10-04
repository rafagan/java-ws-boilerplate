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
        MultivaluedMap<String, Object> headers = containerResponse.getHeaders();
        headers.add(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(ACCESS_CONTROL_ALLOW_HEADERS, "accept, content-type, accept-language, authorization");
        headers.add(ACCESS_CONTROL_ALLOW_METHODS, "GET, PUT, POST, DELETE, PATCH, OPTIONS, HEAD");
    }
}