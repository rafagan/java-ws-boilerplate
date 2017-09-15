package vetorlog.controller.prototype;

import org.glassfish.jersey.server.ContainerRequest;
import vetorlog.api.util.ResponseFactory;
import vetorlog.manager.DatabaseManager;
import vetorlog.manager.I18nManager;
import vetorlog.model.UserModel;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;

public class Controller {
    @Inject
    protected DatabaseManager dbManager;

    @Context
    protected SecurityContext securityContext;

    @Context
    protected Request request;

    @Context
    private ContainerRequestContext containerRequestContext;

    @Inject
    protected ResponseFactory response;

    protected I18nManager i18n() {
        return (I18nManager) containerRequestContext.getProperty("i18n");
    }

    protected UserModel user() {
        return (UserModel) securityContext.getUserPrincipal();
    }
}
