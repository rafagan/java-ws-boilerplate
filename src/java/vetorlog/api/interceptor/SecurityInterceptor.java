package vetorlog.api.interceptor;

import vetorlog.api.util.ResponseFactory;
import vetorlog.api.util.UserTokenUtils;
import vetorlog.conf.Constant;
import vetorlog.manager.I18nManager;
import vetorlog.model.UserModel;
import vetorlog.model.queries.UserQuery;
import vetorlog.util.type.RoleType;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Provider
public class SecurityInterceptor implements ContainerRequestFilter {
    @javax.ws.rs.core.Context
    private ResourceInfo resourceInfo;

    @Inject
    private ResponseFactory response;

    @Inject
    private UserQuery userQueries;

    @Inject
    private UserTokenUtils tokenUtils;

    private boolean isUserAllowed(final UserModel user, final Set<String> rolesSet) {
        boolean exists = user != null;
        if(!exists)
            return false;

        boolean isSuperUser = rolesSet.contains(RoleType.SUPERUSER) && user.isSuperUser();
        boolean isAdmin = rolesSet.contains(RoleType.ADMIN) && userQueries.isAdmin(user);

        return isSuperUser || isAdmin;
    }

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        context.setProperty("i18n", new I18nManager(Constant.EMETER_APP_LOCALE, "responses"));
        I18nManager i18n = (I18nManager) context.getProperty("i18n");
        Method method = resourceInfo.getResourceMethod();

        //Verifying generic permissions (it's all or nothing)
        if(method.isAnnotationPresent(PermitAll.class))
            return;
        if(method.isAnnotationPresent(DenyAll.class))
            context.abortWith(response.forbidden(i18n.get("access_forbidden")));

        //Verify user access
        if(method.isAnnotationPresent(RolesAllowed.class)) {
            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

            //Fetch authorization header
            final String token = context.getHeaderString("Authorization");

            //If no authorization information present: block access
            Response unauthorized = response.unauthorized(i18n.get("unauthorized_token"), true);
            if(token == null || token.isEmpty())
                context.abortWith(unauthorized);

            // Get the user
            UserModel user = tokenUtils.readUser(token);

            //Is user valid?
            if(isUserAllowed(user, rolesSet)) {
                context.setProperty("user", user);
                String scheme = context.getUriInfo().getRequestUri().getScheme();
                context.setSecurityContext(new SessionContext(user, scheme, i18n));
            } else
                context.abortWith(unauthorized);
        }
    }
}