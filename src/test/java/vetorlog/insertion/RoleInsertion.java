package vetorlog.insertion;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import vetorlog.model.RoleModel;
import vetorlog.conf.ResourceLocalTestConfig;
import vetorlog.util.type.RoleType;

import java.util.HashMap;

public class RoleInsertion extends ResourceLocalTestConfig {
    @Test
    @SneakyThrows
    void insert() {
        RoleModel role;

        role = dbManager.findOrCreate(RoleModel.class, new HashMap<String, Object>() {{
            put("name", RoleType.USER);
            put("admin", false);
            put("user", true);
        }});
        dbManager.insert(role);

        role = dbManager.findOrCreate(RoleModel.class, new HashMap<String, Object>() {{
            put("name", RoleType.ADMIN);
            put("admin", true);
            put("user", false);
        }});
        dbManager.insert(role);
    }
}
