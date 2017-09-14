package vetorlog.api.interceptor;

import vetorlog.conf.Constant;
import vetorlog.manager.I18nManager;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class RequestInterceptor implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext context) throws IOException {

    }
}