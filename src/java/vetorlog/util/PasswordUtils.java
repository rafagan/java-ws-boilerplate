package vetorlog.util;

import org.apache.commons.codec.binary.Base64;
import vetorlog.conf.Constant;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;

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

    private static String readSalt(String password) {
        return isValidPassword(password) ? password.split("\\$")[0] : null;
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

    /**
     * Gera a senha criptografada a partir da chave texto inserida pelo usuário para comparação a fins de autenticação
     * e paga salvamento seguro da senha no banco. A ideia é que a criptografia seja só de ida, ou seja, é impossível
     * descobrir a senha do usuário a partir do que está salvo no banco de dados, mas é possível gerar o hash para
     * entender se as senha do usuário gera o mesmo hash que a salva no banco, permitindo autenticação do mesmo
     * @param password Senha inserida pelo usuário
     * @param salt Hash que faz com que dois passwords iguais gerem hashes diferentes.
     *             Se for null, gera o salt, caso contrário o utiliza para gerar o password para comparação
     * @return the generated token
     */
    public static String genHashPassword(String password, String salt) throws Exception {
        salt = salt != null ? salt : genSalt();
        byte[] bSalt = base64ToByte(salt);
        byte[] bDigest = genPasswordHashWithSalt(Constant.SALT_ITERATION_NUMBER, password, bSalt);

        return MessageFormat.format("{0}${1}", salt, byteToBase64(bDigest));
    }

    public static String readPassword(String passwordHash) {
        return isValidPassword(passwordHash) ? passwordHash.split("\\$")[1] : null;
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.contains("$");
    }

    public static boolean checkPassword(String password) throws Exception {
        String rawHash = PasswordUtils.genHashPassword(password, readSalt(password));
        return password != null && rawHash.equals(password);
    }
}
