package vetorlog.api.interceptor;

import vetorlog.api.util.ResponseFactory;
import vetorlog.conf.Constant;
import vetorlog.manager.DatabaseManager;
import vetorlog.manager.I18nManager;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

@Provider
public class SecurityInterceptor implements ContainerRequestFilter {
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String VENDOR_ROLE = "VENDOR";

    private static final String AUTHORIZATION_PROPERTY = "Authorization";

    @Inject
    private ResponseFactory response;

    @Inject
    private DatabaseManager dbManager;

    private I18nManager inManager = new I18nManager(Constant.EMETER_APP_LOCALE, "responses");

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        Method method = resourceInfo.getResourceMethod();

        if(method.isAnnotationPresent(PermitAll.class))
            return;
        if(method.isAnnotationPresent(DenyAll.class))
            context.abortWith(response.forbidden(inManager.get("access_forbidden")));

        context.setProperty("i18n", inManager);

        final Response INVALID_TOKEN =
                response.unauthorized("Access denied for this resource (Reason: Invalid Token)", true);
//
//
//        //Verify user access
//        if(method.isAnnotationPresent(RolesAllowed.class)) {
//            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
//            Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
//
//            //Fetch authorization header
//            final String authorization = context.getHeaderString(AUTHORIZATION_PROPERTY);
//
//            //If no authorization information present; block access
//            if(authorization == null || authorization.isEmpty()) {
//                context.abortWith(INVALID_TOKEN);
//            }
//
//            // Get the user
//            UserModel user = Token.getUserFromToken(authorization, sqlRepo);
//
//            //Is user valid?
//            if(isUserAllowed(user, rolesSet)) {
//                ResteasyProviderFactory.pushContext(User.class, user);
//            } else {
//                context.abortWith(INVALID_TOKEN);
//            }
//        }
    }

//    private boolean isUserAllowed(final User user, final Set<String> rolesSet) {
//
//        return user != null &&
//                (rolesSet.contains(ADMIN_ROLE) && user.getAdministrator() != null
//                        || rolesSet.contains(VENDOR_ROLE) && user.getVendor() != null);
//    }
}