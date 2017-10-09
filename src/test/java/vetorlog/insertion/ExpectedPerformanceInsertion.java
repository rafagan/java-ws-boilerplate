package vetorlog.insertion;

import lombok.val;
import org.junit.jupiter.api.Test;
import vetorlog.prototype.ResourceLocalTestConfig;
import vetorlog.model.ExpectedPerformanceModel;
import vetorlog.model.TypologyPumpModel;
import vetorlog.model.UserModel;

import java.util.Random;

public class ExpectedPerformanceInsertion extends ResourceLocalTestConfig {
    @Test
    void insert() {
        Random rand = new Random();
        val pumps = dbManager.all(TypologyPumpModel.class);

        for(int i = 0; i < 100; i++) {
            ExpectedPerformanceModel model = new ExpectedPerformanceModel();
            model.setUser(dbManager.first(UserModel.class));
            model.setTypologyPump(pumps.get(rand.nextInt(pumps.size())));
            model.setMinimum(rand.nextDouble());
            model.setMaximum(model.getMinimum() + rand.nextDouble());
            model.setMedian(rand.nextDouble());
            model.setSatisfactory(rand.nextDouble());
            model.setUnsatisfactory(rand.nextDouble());
            model.setWithoutCredibility(rand.nextDouble());

            dbManager.insert(model);
        }
    }
}
