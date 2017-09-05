package vetorlog.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class App extends ResourceConfig {
    public App() {
//        register(RequestInterceptor.class);
//        register(JacksonFeature.class);
//        register(MultiPartFeature.class);
//        register(AppServletContextListener.class);
//        register(ExceptionInterceptor.class);

        packages("vetorlog.api;"); //com.wordnik.swagger.jaxrs.listing;
        property(ServerProperties.TRACING, "ALL");

//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setTitle("Boomb");
//        beanConfig.setVersion("1.0");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setBasePath("api");
//        beanConfig.setResourcePackage("com.guizion.boomb.resource");
//        beanConfig.setScan(true);
//        beanConfig.setHost(Utils.localhostOrServerUrl(C.msDescriptor.getRestUrl()));
    }
}
