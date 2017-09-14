package vetorlog.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vetorlog.conf.Constant;

import java.util.Calendar;
import java.util.Date;

public class TokenUtils {
    /**
     * Default token generator token with 60 days validity
     * @param userId the user id which will be hashed within the token
     * @return the generated token
     */
    public static <T> String generateTokenWithId(T userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 60);

        return generateTokenWithId(userId, calendar);
    }

    /**
     * Token generator with custom expiration time
     * @param userId the user id which will be hashed within the token
     * @param expiration an time in future which the token will be invalidated
     * @return the generated token
     */
    public static <T> String generateTokenWithId(T userId, Calendar expiration) {
        return Jwts.builder().setIssuer(String.valueOf(userId))
                .setExpiration(expiration.getTime())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, Constant.SECRET)
                .compact();
    }
}