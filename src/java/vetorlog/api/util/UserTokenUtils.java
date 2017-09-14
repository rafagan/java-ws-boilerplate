package vetorlog.api.util;

import io.jsonwebtoken.*;
import org.jvnet.hk2.annotations.Service;
import vetorlog.conf.Constant;
import vetorlog.model.UserModel;
import vetorlog.manager.DatabaseManager;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Map;

@Service
public class UserTokenUtils {
    @Inject
    private DatabaseManager dbManager;

    public UserModel readUser(String token) {
        try {
            Jws<Claims> _token = Jwts.parser().setSigningKey(Constant.SECRET).parseClaimsJws(token);
            Long userId = Long.parseLong(_token.getBody().getIssuer());
            return dbManager.find(UserModel.class, userId);
        } catch (Exception e) {
            return null;
        }
    }

    public static String generateTokenFromDictionary(String id, Map<String, String> map, Calendar calendar) {
        JwtBuilder tokenGen = Jwts.builder().setSubject(id).setExpiration(calendar.getTime());

        for(Map.Entry<String, String> x : map.entrySet())
            tokenGen.setHeaderParam(x.getKey(), x.getValue());

        return tokenGen.signWith(SignatureAlgorithm.HS256, Constant.SECRET).compact();
    }

//    public static IModel getUserFromIdToken(String token, SqlRepository repo) {
//        try {
//            Jws<Claims> _token = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
//            return repo.getById(IModel.class, Long.parseLong(_token.getBody().getSubject()));
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
