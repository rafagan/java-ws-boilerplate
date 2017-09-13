package vetorlog.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import org.apache.commons.codec.binary.Base64;
import vetorlog.conf.Constant;
import vetorlog.model.queries.ExampleQuery;

public class PasswordUtils {
    private static byte[] base64ToByte(String data) throws IOException {
        return Base64.decodeBase64(data);
    }

    private static String byteToBase64(byte[] data){
        return Base64.encodeBase64String(data);
    }

    private static String genSalt() throws NoSuchAlgorithmException {
        byte[] bSalt = new byte[8];

        SecureRandom random;
        random = SecureRandom.getInstance("SHA1PRNG");
        random.nextBytes(bSalt);

        return byteToBase64(bSalt);
    }

    private static byte[] genPasswordHashWithSalt(int iteration, String password, byte[] salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);

        byte[] result = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < iteration; i++) {
            digest.reset();
            result = digest.digest(result);
        }

        return result;
    }

    public static String genHashPassword(String password) throws Exception {
        String salt = genSalt();
        byte[] bSalt = base64ToByte(salt);
        byte[] bDigest = genPasswordHashWithSalt(Constant.SALT_ITERATION_NUMBER, password, bSalt);

        return MessageFormat.format("{0}${1}", salt, byteToBase64(bDigest));
    }
}
