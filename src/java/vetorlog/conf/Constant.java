package vetorlog.conf;

import lombok.extern.log4j.Log4j2;
import vetorlog.util.types.EnvironmentType;
import vetorlog.util.types.PersistenceContextType;

import static vetorlog.util.ConfUtils.readDatabaseContextFromPersistenceXml;

@Log4j2
public class Constant {
    public static final String DEFAULT_PAGE_SIZE = "100";
    public final static int SALT_ITERATION_NUMBER = 1000;

    public static final String EMETER_APP_DATABASE = initDatabaseName();
    public static final String EMETER_APP_ENVIRONMENT = initEnvironmentName();
    public static final EnvironmentType ENVIRONMENT = initEnvironment();
    public static final String EMETER_APP_URL = initAppUrl();
    public static final PersistenceContextType DATABASE_CONTEXT = initDatabaseContext();
    public static final Boolean EMETER_APP_USE_SSL = useSSL();
    private static Boolean useSSL() {
        return Boolean.valueOf(System.getenv().get("EMETER_APP_USE_SSL"));
    }

    private static String initDatabaseName() {
        return System.getenv().get("EMETER_APP_DATABASE");
    }

    private static String initEnvironmentName() {
        return System.getenv().get("EMETER_APP_ENVIRONMENT");
    }

    private static EnvironmentType initEnvironment() {
        try {
            return EnvironmentType.valueOf(Constant.EMETER_APP_ENVIRONMENT.toUpperCase());
        } catch(NullPointerException e) {
            log.warn("EMETER_APP_ENVIRONMENT is not set, please run the commands from one of the shell scripts " +
                    "to set appropriately the environment variables. See README.md for more details.");
        }
        return null;
    }

    private static String initAppUrl() {
        return System.getenv().get("EMETER_APP_URL");
    }

    private static PersistenceContextType initDatabaseContext() {
        return readDatabaseContextFromPersistenceXml();
    }
}