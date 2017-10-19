package vetorlog.conf;

import lombok.extern.log4j.Log4j2;
import vetorlog.util.type.EnvironmentType;
import vetorlog.util.type.PersistenceContextType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static vetorlog.util.ConfUtils.readDatabaseContextFromPersistenceXml;

/**
 * Constantes do projeto
 * Todas as constantes do shell script devem ser lidas nesta classe, a fim de serem acessadas de forma mais
 * centralizada e organizada, facilitando a codificação e depuração das mesmas
 */
@Log4j2
public class Constant {
    // Hash utilizado na geração de tokens
    public static final String SECRET = "k#r4uCZ#4fBhIKb05o#ezioLf4M0LqtWZ#iGKqtlOQAzJN0aNGfp3e#EG#m4Mv";
    // Tamanho padrão das oaginações
    public static final String DEFAULT_PAGE_SIZE = "100";
    // Total de iterações de aumento de segurança do hash da senha (quanto maior, mais segura a senha, porém mais pesado o algoritmo)
    public static final int SALT_ITERATION_NUMBER = 1000;
    // Idiomas suportados pela camada de internacionalização
    public static final Set<String> SUPPORTED_LANGUAGES = new HashSet<>(Arrays.asList("pt", "en"));
    // Países suportados pela camada de internacionalização
    public static final Set<String> SUPPORTED_COUNTRIES = new HashSet<>(Arrays.asList("BR", "US"));

    // Strings de documentação dos retornos de erro no Swagger
    public static final String CODE_ERROR_400 = "An error has ocurred";
    public static final String CODE_ERROR_500 = "An internal error has ocurred";
    public static final String CODE_ERROR_401 = "Unauthorized access";

    // Leitura das variáveis definidas no shell script de inicialização (mais informações no README.MD)
    public static final String EMETER_APP_DATABASE = initDatabaseName();
    public static final String EMETER_APP_ENVIRONMENT = initEnvironmentName();
    public static final EnvironmentType ENVIRONMENT = initEnvironment();
    public static final String EMETER_APP_URL = initAppUrl();
    public static final PersistenceContextType DATABASE_CONTEXT = initDatabaseContext();
    public static final Boolean EMETER_APP_USE_SSL = useSSL();
    public static final String EMETER_APP_LOCALE = initLocale();

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

    private static String initLocale() {
        return System.getenv().get("EMETER_APP_LOCALE");
    }
}