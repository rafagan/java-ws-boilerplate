package vetorlog.conf;

import io.sentry.Sentry;
import io.swagger.jaxrs.config.BeanConfig;
import lombok.extern.log4j.Log4j2;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import vetorlog.conf.cdi.JerseyCDIAppBinder;

import javax.ws.rs.ApplicationPath;

@Log4j2
@ApplicationPath("api")
public class App extends ResourceConfig {
    private void confJersey2() {
        packages("vetorlog.api;");
        property(ServerProperties.TRACING, "ALL");
    }

    private void confDependencyInjection() {
        register(new JerseyCDIAppBinder());
    }

    private void confSentry() {
        Sentry.init();
    }

    private void confSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Econometer");
        beanConfig.setDescription("Documentação das requisições do projeto e interface de teste");
        beanConfig.setVersion("1.0");
        beanConfig.setResourcePackage("vetorlog.api");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
        beanConfig.setBasePath("/api");
        beanConfig.setSchemes(new String[]{"http", "https"});
        beanConfig.setHost(Constants.EMETER_APP_URL);

        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
    }

    public App() {
        confJersey2();
        confDependencyInjection();
        confSentry();
        confSwagger();
    }
}

