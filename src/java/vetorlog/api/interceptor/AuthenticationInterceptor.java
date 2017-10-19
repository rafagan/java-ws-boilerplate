package vetorlog.api.interceptor;

import vetorlog.api.util.ResponseFactory;
import vetorlog.api.util.UserTokenUtils;
import vetorlog.conf.Constant;
import vetorlog.manager.I18nManager;
import vetorlog.model.UserModel;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Interceptor de autenticação
 * Intercepta a requisição para identificar se o usuário possui permissão de acesso ao método
 * Essas permissões envolvem acesso total (PermitAll), acesso bloqueado (DenyAll) e token autorizado
 * Para que o token seja validado, é obrigatório que alguma permissão esteja associada ao método
 * Os papéis (RolesAllowed) são validados no interceptor de autorização (AuthorizationInterceptor)
 */
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

        //Verify if is necessary authentication
        Method method = resourceInfo.getResourceMethod();
        if(method.isAnnotationPresent(PermitAll.class))
            return;
        if(method.isAnnotationPresent(DenyAll.class))
            context.abortWith(response.forbidden(i18n.get("access_forbidden")));

        //Verify user access
        if(method.isAnnotationPresent(RolesAllowed.class)) {
            //Fetch authorization header
            final String token = context.getHeaderString("Authorization");

            //If no authorization information present: block access
            if (token == null || token.isEmpty())
                context.abortWith(response.unauthorized(i18n.get("unauthorized_token"), true));

            // Finally, initialize session variables
            UserModel user = tokenUtils.readUser(token);
            context.setProperty("user", user);
            String scheme = context.getUriInfo().getRequestUri().getScheme();
            context.setSecurityContext(new AuthorizationInterceptor(user, scheme, i18n));
        }
    }
}