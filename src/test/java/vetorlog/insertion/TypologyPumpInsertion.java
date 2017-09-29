package vetorlog.insertion;

import lombok.val;
import org.junit.jupiter.api.Test;
import vetorlog.conf.ResourceLocalTestConfig;
import vetorlog.model.GroupSitesModel;
import vetorlog.model.TypologyPumpModel;

import java.util.Random;

public class TypologyPumpInsertion extends ResourceLocalTestConfig {
    private String[] descriptions = new String[]{"COMBO_TIPOLOGIA", "nome 1", "nome 2", "nome 3", "nome 4", "nome 5"};
    private String[] observations = new String[]{"teste 1", "teste 2", "teste 3", "teste 4", "teste 5", "teste 6"};

    @Test
    void insert() {
        for(int i = 0; i < descriptions.length; i++) {
            TypologyPumpModel model = new TypologyPumpModel();
            String description = descriptions[i];
            String observation = observations[i];
            model.setDescription(description);
            model.setObservations(observation);
            dbManager.insert(model);
        }
    }

    @Test
    void relateWithGroupSites() {
        Random rand = new Random();
        val groups = dbManager.all(GroupSitesModel.class);
        val pumps = dbManager.all(TypologyPumpModel.class);

        for(int i = 0; i < 30; i++) {
            val group = groups.get(rand.nextInt(groups.size()));
            val pump = pumps.get(rand.nextInt(pumps.size()));
            group.getTypologyPumps().add(pump);
        }

        for(val group: groups)
            dbManager.update(group);
    }
}
