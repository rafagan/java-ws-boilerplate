package vetorlog.util;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vetorlog.conf.Constant;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class TokenUtils {
    /**
     * Default token generator token with 60 days validity
     * @param id the user id which will be hashed within the token
     * @return the generated token
     */
    public static String generateToken(String id) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 60);

        return generateToken(id, calendar);
    }

    /**
     * Token generator with custom expirationDate time
     * @param id the id which will be hashed within the token
     * @param expirationDate an time in future which the token will be invalidated
     * @return the generated token
     */
    public static String generateToken(String id, Calendar expirationDate) {
        return Jwts.builder()
                .setIssuer(String.valueOf(id))
                .setSubject(String.valueOf(id))
                .setExpiration(expirationDate.getTime())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, Constant.SECRET)
                .compact();
    }

    /**
     * Gera o Token da sessão, a qual permite identificar, por exemplo, se o usuário está autenticado, a partir do id
     * e informações extras passadas via dicionário. As informações extras serão criptografadas e podem se extraídas
     * posteriormente
     * @param id the id which will be hashed within the token
     * @param parameters parâmetros a criptografar
     * @param expirationDate data de expiração do token
     * @return String do Token
     */
    public static String generateToken(String id, Map<String, String> parameters, Calendar expirationDate) {
        JwtBuilder tokenGen = Jwts.builder()
                .setIssuer(String.valueOf(id))
                .setSubject(String.valueOf(id))
                .setExpiration(expirationDate.getTime())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, Constant.SECRET);

        for(Map.Entry<String, String> x : parameters.entrySet())
            tokenGen.setHeaderParam(x.getKey(), x.getValue());

        return tokenGen.compact();
    }
}