/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetorlog.conf;

import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.util.PropertiesHelper;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 *
 * @author temp
 */
public class JacksonConf implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        final String disableMoxy = PropertiesHelper.getPropertyNameForRuntime(
                CommonProperties.MOXY_JSON_FEATURE_DISABLE,
                context.getConfiguration().getRuntimeType());
        context.property(disableMoxy, true);

        // add the default Jackson exception mappers and allow jaxb annotations
        context.register(JsonParseExceptionMapper.class);
        context.register(JacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
        return true;
    }
}
