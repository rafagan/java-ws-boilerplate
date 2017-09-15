package vetorlog.api.interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import vetorlog.manager.I18nManager;
import vetorlog.model.UserModel;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Data
@AllArgsConstructor
public class SessionContext implements SecurityContext {
    private UserModel user;
    private String scheme;
    private I18nManager i18n;

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isUserInRole(String s) {
        return user.getRole() != null && user.getRole().getName().equals(s);
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
