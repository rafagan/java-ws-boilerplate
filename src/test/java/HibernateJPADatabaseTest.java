import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vetorlog.model.util.relational.DatabaseManager;

class HibernateJPADatabaseTest {
    private static final Logger logger = LogManager.getRootLogger();

    @Inject
    private DatabaseManager dbManager;
}
