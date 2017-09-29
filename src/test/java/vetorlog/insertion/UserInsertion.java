package vetorlog.insertion;

import org.junit.jupiter.api.Test;
import vetorlog.model.RoleModel;
import vetorlog.model.UserModel;
import vetorlog.model.adapter.UserAdapter;
import vetorlog.model.util.relational.WrapperDefault;
import vetorlog.conf.ResourceLocalTestConfig;
import vetorlog.util.PasswordUtils;

import java.util.HashMap;
import java.util.Random;

public class UserInsertion extends ResourceLocalTestConfig {
    private String[] hosts = new String[]{"joao", "jose", "sheila", "adamastor", "fernando", "alan", "lucas"};
    private String[] domains = new String[]{"hotmail", "gmail", "outlook", "me", "icloud", "yahoo"};

    @Test
    void insert() throws Exception {
        Random rand = new Random();

        RoleModel role = dbManager.findOrCreate(RoleModel.class, new HashMap<String, Object>() {{
            put("name", "Admin");
        }});

        for(int i = 0; i < 10; i++) {
            UserModel model = new UserModel();
            String host = hosts[rand.nextInt(hosts.length)];
            String domain = domains[rand.nextInt(domains.length)];

            model.setEmail(String.format("%s@%s.com", host, domain));
            model.setName(host);
            model.setPassword(PasswordUtils.generateHashPassword("abcd@1234", null));
            model.setRole(role);

            if(new UserAdapter(new WrapperDefault()).findUserByEmail(model.getEmail()) == null)
                dbManager.insert(model);
        }
    }
}
