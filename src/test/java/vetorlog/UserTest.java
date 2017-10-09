package vetorlog;

import org.junit.jupiter.api.Test;
import vetorlog.prototype.ResourceLocalTestConfig;
import vetorlog.api.util.UserTokenUtils;
import vetorlog.model.UserModel;

class UserTest extends ResourceLocalTestConfig {
//    @Test
    void readDataFromToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiI5ZTdiODAzZS02MDI5LTQ4MWMtODIzMS03OTdjNWE4ZTAwMGYiLCJzdWIiOiI5ZTdiODAzZS02MDI5LTQ4MWMtODIzMS03OTdjNWE4ZTAwMGYiLCJleHAiOjE1MTA2MTc2MjIsImlhdCI6MTUwNTQzNzIyMn0.CbGfMTOduh2AJ40Kb3bmNYMqXJemPI9BarKmP6EzxVg";
        UserModel model = new UserTokenUtils(dbManager).readUser(token);
        System.out.println(model.getName());
        System.out.println(model.getEmail());
    }
}