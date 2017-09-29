package vetorlog.examples;

import org.junit.jupiter.api.Test;
import vetorlog.api.util.UserTokenUtils;
import vetorlog.model.RoleModel;
import vetorlog.model.UserModel;
import vetorlog.model.adapters.UserAdapter;
import vetorlog.model.util.relational.WrapperDefault;
import vetorlog.util.PasswordUtils;

import java.util.HashMap;
import java.util.Random;

class UserTest extends ResourceLocalTestConfig {
    private String[] hosts = new String[]{"joao", "jose", "sheila", "adamastor", "fernando", "alan", "lucas"};
    private String[] domains = new String[]{"hotmail", "gmail", "outlook", "me", "icloud", "yahoo"};

    @Test
    void insertUserTest() throws Exception {
        Random rand = new Random();

        RoleModel role = dbManager.findOrCreate(RoleModel.class, new HashMap<String, Object>() {{
            put("name", "Admin");
        }});
        role = dbManager.insert(role);

        for(int i = 0; i < 10; i++) {
            UserModel model = new UserModel();
            String host = hosts[rand.nextInt(hosts.length)];
            String domain = domains[rand.nextInt(domains.length)];

            model.setEmail(String.format("%s@%s.com", host, domain));
            model.setName(host);
            model.setPassword(PasswordUtils.generateHashPassword(domain, null));
            model.setRole(role);

            if(new UserAdapter(new WrapperDefault()).findUserByEmail(model.getEmail()) == null)
                dbManager.insert(model);
        }
    }

    @Test
    void readDataFromToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiI5ZTdiODAzZS02MDI5LTQ4MWMtODIzMS03OTdjNWE4ZTAwMGYiLCJzdWIiOiI5ZTdiODAzZS02MDI5LTQ4MWMtODIzMS03OTdjNWE4ZTAwMGYiLCJleHAiOjE1MTA2MTc2MjIsImlhdCI6MTUwNTQzNzIyMn0.CbGfMTOduh2AJ40Kb3bmNYMqXJemPI9BarKmP6EzxVg";
        UserModel model = new UserTokenUtils(dbManager).readUser(token);
        System.out.println(model.getName());
        System.out.println(model.getEmail());
    }
}