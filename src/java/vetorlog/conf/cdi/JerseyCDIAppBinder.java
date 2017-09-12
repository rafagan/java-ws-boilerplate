package vetorlog.conf.cdi;

import lombok.extern.log4j.Log4j2;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import vetorlog.conf.Constants;
import vetorlog.controller.ExampleController;
import vetorlog.model.queries.ExampleQuery;
import vetorlog.model.util.relational.*;

@Log4j2
public class JerseyCDIAppBinder extends AbstractBinder {
    @SuppressWarnings("RedundantToBinding")
    @Override
    protected void configure() {
        bind(DatabaseManager.class).to(DatabaseManager.class);
        bind(ExampleController.class).to(ExampleController.class);
        bind(ExampleQuery.class).to(ExampleQuery.class);

        switch(Constants.ENVIRONMENT) {
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