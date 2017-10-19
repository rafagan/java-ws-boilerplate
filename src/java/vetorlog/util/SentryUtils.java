package vetorlog.util;

import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;

/**
 * Camada de comunicação direta com o Sentry (ver configuração em sentry.properties)
 * O sistema atual também suporta integração do Sentry com o Log4J2 (ver log4j2.xml)
 * Para mais informações, https://docs.sentry.io/clients/java/usage/
 */
public class SentryUtils {
    private SentryClient sentryClient;

    private SentryClient getSentryClient() {
        if(sentryClient == null)
            sentryClient = SentryClientFactory.sentryClient();
        return sentryClient;
    }

    public SentryUtils addExtra(String key, Object value) {
        getSentryClient().addExtra(key, value);
        return this;
    }

    public SentryUtils addTag(String key, String value) {
        getSentryClient().addTag(key, value);
        return this;
    }

    public <T> void debug(String text, Class<T> eventClass) {
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(text)
                .withLevel(Event.Level.WARNING)
                .withLogger(eventClass.getName());

        getSentryClient().sendEvent(eventBuilder);
    }

    public <T> void info(String text, Class<T> eventClass) {
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(text)
                .withLevel(Event.Level.INFO)
                .withLogger(eventClass.getName());

        getSentryClient().sendEvent(eventBuilder);
    }

    public <T> void warn(String text, Class<T> eventClass) {
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(text)
                .withLevel(Event.Level.WARNING)
                .withLogger(eventClass.getName());

        getSentryClient().sendEvent(eventBuilder);
    }

    public <T> void error(String text, Class<T> eventClass) {
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(text)
                .withLevel(Event.Level.WARNING)
                .withLogger(eventClass.getName());

        getSentryClient().sendEvent(eventBuilder);
    }

    public <T> void fatal(String text, Class<T> eventClass) {
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(text)
                .withLevel(Event.Level.WARNING)
                .withLogger(eventClass.getName());

        getSentryClient().sendEvent(eventBuilder);
    }

    public void exception(Throwable exception) {
        getSentryClient().sendException(exception);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        sentryClient.clearContext();
        sentryClient.closeConnection();
    }
}
