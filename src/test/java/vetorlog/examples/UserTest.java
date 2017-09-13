package vetorlog.examples;

import org.junit.jupiter.api.Test;
import vetorlog.model.UserModel;
import vetorlog.util.PasswordUtils;

import java.util.Random;

class UserTest extends ResourceLocalTestConfig {
    private String[] hosts = new String[]{"joao", "jose", "sheila", "adamastor", "fernando", "alan", "lucas"};
    private String[] domains = new String[]{"hotmail", "gmail", "outlook", "me", "icloud", "yahoo"};

    @Test
    void insertUserTest() throws Exception {
        Random rand = new Random();

        for(int i = 0; i < 10; i++) {
            UserModel model = new UserModel();
            String host = hosts[rand.nextInt() % hosts.length];
            String domain = domains[rand.nextInt() % hosts.length];

            model.setEmail(String.format("%s@%s.com", host, domain));
            model.setUsername(host);
            model.setPassword(PasswordUtils.genHashPassword(domain));

            dbManager.insert(model);
        }
    }
}