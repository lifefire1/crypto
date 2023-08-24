package DH;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Crypto {
    private static final String ALGORITHM = "DES";

    public static String encrypt(String message, long rounds) throws Exception {
        Cipher desCipher = Cipher.getInstance(ALGORITHM);
        byte[] keyBytes = (rounds + "cryptography is cool!").getBytes(StandardCharsets.UTF_8);

        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);

        desCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = desCipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedMessage, long rounds) throws Exception {
        Cipher desCipher = Cipher.getInstance(ALGORITHM);
        byte[] keyBytes = (rounds + "cryptography is cool!").getBytes(StandardCharsets.UTF_8);

        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);

        desCipher.init(Cipher.DECRYPT_MODE, secretKey);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedBytes = decoder.decode(encryptedMessage);

        byte[] decryptedBytes = desCipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
