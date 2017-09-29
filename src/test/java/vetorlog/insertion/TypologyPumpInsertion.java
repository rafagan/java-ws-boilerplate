package vetorlog.insertion;

import org.junit.jupiter.api.Test;
import vetorlog.conf.ResourceLocalTestConfig;
import vetorlog.model.TypologyPumpModel;

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
}
