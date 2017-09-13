//package vetorlog.api.interceptor;
//
//import vetorlog.api.util.ResponseFactory;
//import vetorlog.model.User;
//import vetorlog.model.util.relational.DatabaseManager;
//
//import javax.annotation.security.DenyAll;
//import javax.annotation.security.PermitAll;
//import javax.annotation.security.RolesAllowed;
//import javax.inject.Inject;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//@Provider
//public class SecurityInterceptor implements ContainerRequestFilter {
//    public static final String ADMIN_ROLE = "ADMIN";
//    public static final String VENDOR_ROLE = "VENDOR";
//
//    private static final String AUTHORIZATION_PROPERTY = "Authorization";
//    private static final String AUTHENTICATION_SCHEME = ""; // i.e. Basic, Token, Bearer
//    private static final Response ACCESS_FORBIDDEN = ResponseFactory.forbidden("Nobody can access this resource");
//    private static final Response INVALID_TOKEN = ResponseFactory.unauthorized("Access denied for this resource (Reason: Invalid Token)", true);
//
//    @Inject
//    private DatabaseManager manager;
//
//    @Override
//    public void filter(ContainerRequestContext context) throws IOException {
//        PostMatchContainerRequestContext pmContext = (PostMatchContainerRequestContext) context;
//
//        Method method = pmContext.getResourceMethod().getMethod();
//
//        //Access allowed for all
//        if(method.isAnnotationPresent(PermitAll.class)) {
//            return;
//        }
//        //Access denied for all
//        if(method.isAnnotationPresent(DenyAll.class)) {
//            context.abortWith(ACCESS_FORBIDDEN);
//        }
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
//            //Get only token value
////            final String token = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
//
//            //If no authorization information present; block access
//            if(authorization == null || authorization.isEmpty()) {
//                context.abortWith(INVALID_TOKEN);
//            }
//
//            // Get the user
//            User user = Token.getUserFromToken(authorization, sqlRepo);
//
//            //Is user valid?
//            if(isUserAllowed(user, rolesSet)) {
//                ResteasyProviderFactory.pushContext(User.class, user);
//            } else {
//                context.abortWith(INVALID_TOKEN);
//            }
//        }
//    }
//
//    private boolean isUserAllowed(final User user, final Set<String> rolesSet) {
//
//        return user != null &&
//                (rolesSet.contains(ADMIN_ROLE) && user.getAdministrator() != null
//                        || rolesSet.contains(VENDOR_ROLE) && user.getVendor() != null);
//    }
//}