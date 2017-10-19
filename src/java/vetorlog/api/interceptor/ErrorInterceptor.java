package vetorlog.api.interceptor;

import lombok.extern.log4j.Log4j2;
import vetorlog.api.util.ResponseFactory;
import vetorlog.conf.Constant;
import vetorlog.manager.I18nManager;

import javax.inject.Inject;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Interceptor de erros
 * Responsável por capturar retornos de erro da camada JAXRS e realizar o log ao sentry e retornos apropriados
 */
@Provider
@Log4j2
public class ErrorInterceptor implements ExceptionMapper<Exception> {
    private I18nManager i18n = new I18nManager(Constant.EMETER_APP_LOCALE, "responses");

    @Inject
    private ResponseFactory response;

    @Override
    public Response toResponse(Exception e) {
        if(e instanceof NotAcceptableException)
            return response.badRequest(i18n.get("not_acceptable_exception"));
        log.error(e);
        e.printStackTrace();
        return response.internalServerError(e.getMessage());
    }
}
