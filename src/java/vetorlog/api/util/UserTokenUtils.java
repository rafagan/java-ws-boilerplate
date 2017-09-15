package vetorlog.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import vetorlog.conf.Constant;
import vetorlog.manager.DatabaseManager;
import vetorlog.model.UserModel;

import javax.inject.Inject;

@Service
@AllArgsConstructor
public class UserTokenUtils {
    @Inject
    private DatabaseManager dbManager;

    /**
     * Encontra o usuário (com UUID) no banco a partir das informações encriptadas no Token
     * @param token String com dados trafegados pela rede com criptografia que validam a sessão
     * @return Usuário correspondente ao Token
     */
    public UserModel readUser(String token) {
        try {
            Jws<Claims> _token = Jwts.parser().setSigningKey(Constant.SECRET).parseClaimsJws(token);
            String userId = _token.getBody().getIssuer();
            return dbManager.find(UserModel.class, userId);
        } catch (Exception e) {
            return null;
        }
    }
}
