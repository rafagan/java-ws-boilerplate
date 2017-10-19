package vetorlog.manager;

import lombok.NoArgsConstructor;
import lombok.val;
import vetorlog.conf.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.*;

/**
 * Leitura dos arquivos de internacionalização (i18n: i<len(nternacionalizatio) = 18>n)
 */
@NoArgsConstructor
public class I18nManager {
    /**
     * Essa classe é utilizada para configurar o parser dos dados de internacionalização do sistema para UTF-8.
     * Por padrão, o parser trabalha com ISO 8859-1
     */
    private class UTF8Control extends ResourceBundle.Control {
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException
        {
            // The below is a copy of the default implementation.
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }

    private ResourceBundle bundle;

    /**
     * Construtor que lê o bundle de internacionalização (ex: responses_en_US.properties)
     * @param locale qual é o idioma e país, no formato <idioma>_<país>
     * @param bundleName nome do bundle (ex: responses)
     */
    public I18nManager(String locale, String bundleName) {
        if(locale == null)
            locale = "en_US";
        else {
            String language = locale;
            String country = null;
            if (language.contains("_")) {
                String ss[] = language.split("_");
                language = ss[0];
                country = ss[1];
            }

            val contryForLanguage = new HashMap<String, String>() {{
                put("pt", "BR");
                put("en", "US");
            }};

            if (!Constant.SUPPORTED_LANGUAGES.contains(language))
                locale = "en_US";
            else if (country == null || !Constant.SUPPORTED_COUNTRIES.contains(country))
                locale = language + "_" + contryForLanguage.get(language);
        }

        String ss[] = locale.split("_");
        this.bundle = ResourceBundle.getBundle(bundleName, new Locale(ss[0], ss[1]), new UTF8Control());
    }

    /**
     * Lê key do arquivo de internacionalização
     * @param key nome da key
     * @return string internacionalizada
     */
    public String get(String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return ResourceBundle.getBundle(bundle.getBaseBundleName(),
                    new Locale("en", "US"),
                    new UTF8Control()).getString(key);
        }
    }

    /**
     * Lê key do arquivo de internacionalização
     * @param key nome da key
     * @param arguments dados anotados na string de internaciolização ({0}, {1}, ..., {n}) que devem ser substituídos
     * @return string internacionalizada
     */
    public String get(String key, Object... arguments) {
        return MessageFormat.format(this.get(key), arguments);
    }

    public Locale getLocale() {
        return this.bundle.getLocale();
    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
