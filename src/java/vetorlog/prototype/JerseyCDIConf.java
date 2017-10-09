package vetorlog.conf;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jvnet.hk2.annotations.Contract;
import org.jvnet.hk2.annotations.Service;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import vetorlog.manager.DatabaseManager;
import vetorlog.model.util.relational.*;

import java.util.Set;

@Log4j2
public class JerseyCDIConf extends AbstractBinder {
    @SneakyThrows
    private void automaticBinds() {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().setUrls(ClasspathHelper
                        .forPackage("vetorlog")).setScanners(new TypeAnnotationsScanner()));
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Service.class, true);

        for(val type: classes)
            bind(Class.forName(type.getName())).to(Class.forName(type.getName()));
    }

    @Override
    protected void configure() {
        automaticBinds();

        switch(Constant.ENVIRONMENT) {
            case LOCAL:
                bind(WrapperLocal.class).to(IEntityManagerWrapper.class);
                break;
            case STAGING:
                bind(WrapperStaging.class).to(IEntityManagerWrapper.class);
                break;
            case PRODUCTION:
                bind(WrapperProduction.class).to(IEntityManagerWrapper.class);
                break;
            default:
                log.warn("Choosing persistence_unit=default. Please, set DatabaseManager.ENVIRONMENT with " +
                        "one of EnvironmentType enum values.");
                bind(WrapperDefault.class).to(IEntityManagerWrapper.class);
        }
    }
}