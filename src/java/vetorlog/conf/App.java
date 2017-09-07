package vetorlog.conf;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import vetorlog.model.util.relational.DatabaseManager;

import javax.ws.rs.ApplicationPath;
import java.util.Map;

@ApplicationPath("/")
public class App extends ResourceConfig {
    private void confJersey2() {
        packages("vetorlog.api;"); //com.wordnik.swagger.jaxrs.listing;
        property(ServerProperties.TRACING, "ALL");
    }

    private void confGuice() {
//        Injector injector = Guice.createInjector(new BasicModule());
    }

    public App() {
        confJersey2();
        confGuice();

//        register(RequestInterceptor.class);
//        register(JacksonFeature.class);
//        register(MultiPartFeature.class);
//        register(AppServletContextListener.class);
//        register(ExceptionInterceptor.class);

//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setTitle("Boomb");
//        beanConfig.setVersion("1.0");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setBasePath("api");
//        beanConfig.setResourcePackage("com.guizion.boomb.resource");
//        beanConfig.setScan(true);
//        beanConfig.setHost(Utils.localhostOrServerUrl(C.msDescriptor.getRestUrl()));

        Map<String, String> env = System.getenv();
        DatabaseManager.ENVIRONMENT = AppEnvironment.valueOf(env.get("EMETER_APP_ENVIRONMENT"));
    }
}
