package vetorlog.api.interceptor;

import vetorlog.api.util.ResponseFactory;
import vetorlog.api.util.UserTokenUtils;
import vetorlog.conf.Constant;
import vetorlog.manager.I18nManager;
import vetorlog.model.UserModel;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationInterceptor implements ContainerRequestFilter {
    @javax.ws.rs.core.Context
    private ResourceInfo resourceInfo;

    @Inject
    private ResponseFactory response;

    @Inject
    private UserTokenUtils tokenUtils;

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        context.setProperty("i18n", new I18nManager(Constant.EMETER_APP_LOCALE, "responses"));
        I18nManager i18n = (I18nManager) context.getProperty("i18n");

        //Fetch authorization header
        final String token = context.getHeaderString("Authorization");

        //If no authorization information present: block access
        if(token == null || token.isEmpty())
            context.abortWith(response.unauthorized(i18n.get("unauthorized_token"), true));

        // Finally, initialize session variables
        UserModel user = tokenUtils.readUser(token);
        context.setProperty("user", user);
        String scheme = context.getUriInfo().getRequestUri().getScheme();
        context.setSecurityContext(new AuthorizationInterceptor(user, scheme, i18n));
    }
}