package razoom.utils.jackson.encrypt;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Configure {
    // понятное дело его нужно брать из защищенного хранилища в зависимости от стенда.
    // но тут вот так.
    public static final byte[] KEY_BYTES = new byte[]{4, 3, 4, 1, 4, 8, 8, 2, 2, 8, 2, 6, 5, 4, 7, 2};
    public static final String ALGORITHM = "AES";
}
