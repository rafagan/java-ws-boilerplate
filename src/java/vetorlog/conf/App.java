package vetorlog.conf;

import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import vetorlog.model.util.relational.DatabaseManager;

import javax.validation.constraints.Null;
import javax.ws.rs.ApplicationPath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@ApplicationPath("api")
@Log4j2
public class App extends ResourceConfig {
    private PersistenceContextType readDatabaseContextFromPersistenceXml(String globalName) {
        val persistenceConfigPath = System.getProperty("user.dir") + "/src/resources/META-INF/persistence.xml";
        File xmlFile = new File(persistenceConfigPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            val persistence = doc.getChildNodes().item(0);

            for(int i = 0; i < persistence.getChildNodes().getLength(); i++) {
                val child = persistence.getChildNodes().item(i);
                if(Objects.equals(child.getNodeName(), "persistence-unit")) {
                    val attributes = child.getAttributes();
                    val name = attributes.getNamedItem("name").getNodeValue();
                    try {
                        val context = attributes.getNamedItem("transaction-type").getNodeValue();
                        if(Objects.equals(name, globalName))
                            return PersistenceContextType.valueOf(context);
                    } catch (NullPointerException e) {
                        val details = String.format("transaction-type not found for persistence-unit named: %s", name);
                        throw new NullPointerException(details);
                    }
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        log.warn("The persistence context could not be found in persistence.xml. Choosing RESOURCE_LOCAL.");
        return PersistenceContextType.RESOURCE_LOCAL;
    }

    private void confDatabase() {
        String globalName = "";
        try {
            Map<String, String> env = System.getenv();
            globalName = env.get("EMETER_APP_ENVIRONMENT");
            DatabaseManager.ENVIRONMENT = EnvironmentType.valueOf(globalName.toUpperCase());
        } catch(NullPointerException e) {
            log.warn("EMETER_APP_ENVIRONMENT is not set, please run the commands from one of the shell scripts " +
                    "to set appropriately the environment variables. See README.md for more details.");
        }
        DatabaseManager.CONTEXT = readDatabaseContextFromPersistenceXml(globalName);
    }

    private void confJersey2() {
        packages("vetorlog.api;"); //com.wordnik.swagger.jaxrs.listing;
        property(ServerProperties.TRACING, "ALL");
    }

    private void confGuice() {
//        Injector injector = Guice.createInjector(new BasicModule());
    }

    public App() {
        confDatabase();
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
    }
}
