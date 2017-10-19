package vetorlog.conf;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Configurações de serialização com Jackson
 * Mais informações, ver:
 * https://fasterxml.github.io/jackson-databind/javadoc/2.0.0/com/fasterxml/jackson/databind/DeserializationFeature.html
 * https://fasterxml.github.io/jackson-databind/javadoc/2.0.0/com/fasterxml/jackson/databind/SerializationFeature.html
 */
@Provider
public class JacksonConf extends JacksonJaxbJsonProvider {
    public JacksonConf() {
        super.setMapper(new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
                .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true));
    }
}