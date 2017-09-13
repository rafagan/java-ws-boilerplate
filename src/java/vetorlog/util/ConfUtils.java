package vetorlog.util;

import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.w3c.dom.Document;
import vetorlog.conf.Constant;
import vetorlog.util.types.PersistenceContextType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Objects;

@Log4j2
public class ConfUtils {
    @SuppressWarnings("ConstantConditions")
    public static PersistenceContextType readDatabaseContextFromPersistenceXml() {
        try {
            String globalName = String.format("%s_%s", Constant.EMETER_APP_DATABASE, Constant.EMETER_APP_ENVIRONMENT);

            String persistenceConfigPath = ConfUtils.class.getClassLoader()
                    .getResource("META-INF/persistence.xml").getFile();
            File xmlFile = new File(persistenceConfigPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            val persistence = doc.getChildNodes().item(0);

            for (int i = 0; i < persistence.getChildNodes().getLength(); i++) {
                val child = persistence.getChildNodes().item(i);
                if (Objects.equals(child.getNodeName(), "persistence-unit")) {
                    val attributes = child.getAttributes();
                    val name = attributes.getNamedItem("name").getNodeValue();
                    try {
                        val context = attributes.getNamedItem("transaction-type").getNodeValue();
                        if (Objects.equals(name, globalName))
                            return PersistenceContextType.valueOf(context);
                    } catch (NullPointerException e) {
                        val details = String.format("transaction-type not found for persistence-unit named: %s", name);
                        throw new NullPointerException(details);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        log.warn("The persistence context could not be found in persistence.xml. Choosing RESOURCE_LOCAL.");
        return PersistenceContextType.RESOURCE_LOCAL;
    }
}
