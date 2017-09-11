package vetorlog.examples;

import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;
import lombok.extern.log4j.Log4j2;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import vetorlog.util.SentryLogger;

@Log4j2
class SentryTest {
//    private static SentryClient sentry;

    @BeforeClass
    void configureSentry() {
        Sentry.init();
    }

    private void unsafeMethod() {
        throw new UnsupportedOperationException("You shouldn't call this!");
    }

    @Test
    void sendSentryData() {
        Sentry.getContext().recordBreadcrumb(
                new BreadcrumbBuilder().setMessage("User made an action").build()
        );

        Sentry.getContext().setUser(
                new UserBuilder().setEmail("hello@sentry.io").build()
        );

        Sentry.getContext().addExtra("extra", "thing");

        Sentry.getContext().addTag("tagName", "tagValue");

        Sentry.capture("This is a test");

        try {
            unsafeMethod();
        } catch (Exception e) {
            // This sends an exception event to Sentry using the statically stored instance
            // that was created in the ``main`` method.
            Sentry.capture(e);
        }
    }

    @Test
    void sendInfoData() {
        SentryLogger logger = new SentryLogger();
        logger.addExtra("Teste", "123");
        logger.info("Teste", SentryTest.class);
    }

    @Test
    void testLog4j2WithSentry() {
        log.debug("Teste");
        log.fatal("Teste");
    }
}
