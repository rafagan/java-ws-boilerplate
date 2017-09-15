package vetorlog.api.interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import vetorlog.manager.I18nManager;
import vetorlog.model.UserModel;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Data
@AllArgsConstructor
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationInterceptor implements SecurityContext {
    private UserModel user;
    private String scheme;
    private I18nManager i18n;

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isUserInRole(String methodRole) {
        boolean exists = user != null && user.getRole() != null;
        if(!exists)
            return false;

        String role = user.getRole().getName();
        boolean isSuperUser = user.isSuperUser();
        boolean allowed = role.equals(methodRole);

        return isSuperUser || allowed;

    }

    @Override
    public boolean isSecure() {
        return "https".equals(this.scheme);
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
