package vetorlog.insertion;

import org.junit.jupiter.api.Test;
import vetorlog.prototype.ResourceLocalTestConfig;
import vetorlog.model.GroupSitesModel;

public class GroupSitesInsertion extends ResourceLocalTestConfig {
    private String[] names = new String[]{"grupo 1", "grupo 2", "AFM", "grupo 3", "grupo 4", "grupo 5"};

    @Test
    void insert() {
        for(int i = 0; i < names.length; i++) {
            GroupSitesModel model = new GroupSitesModel();
            String name = names[i];
            model.setName(name);
            dbManager.insert(model);
        }
    }
}
