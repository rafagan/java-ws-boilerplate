package vetorlog.mvnignore;

import io.sentry.Sentry;
import lombok.extern.log4j.Log4j2;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import vetorlog.util.SentryUtils;

@Log4j2
class SentryTest {
    @BeforeClass
    void configureSentry() {
        Sentry.init();
    }

    @Test
    void sendInfoData() {
        SentryUtils logger = new SentryUtils();
        logger.addExtra("Teste", "123");
        logger.info("Teste", SentryTest.class);
    }

    @Test
    void testLog4j2WithSentry() {
        log.debug("Teste");
        log.fatal("Saporra sem borda");
    }

    void theException() {
        throw new NullPointerException("Eita");
    }

    @Test
    void testLogSentryException() {
        try {
            theException();
        } catch (NullPointerException e) {
            log.fatal(e);
            new SentryUtils().exception(e);
        }
    }
}
