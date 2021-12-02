package razoom.utils.jackson.encrypt;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CipherLongSerializer extends JsonSerializer<Long> {

    private Cipher encipher = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(CipherLongSerializer.class);

    public CipherLongSerializer() {
        try {
            encipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(Configure.KEY_BYTES, Configure.ALGORITHM);
            encipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (encipher != null) {
            try {
                byte[] bytes = encipher.doFinal(aLong.toString().getBytes(StandardCharsets.UTF_8));
                bytes = Base64.getEncoder().encode(bytes);
                jsonGenerator.writeString(new String(bytes));
            } catch (IllegalBlockSizeException | BadPaddingException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Cipher for EncryptLongSerializer not initialized");
        }
    }
}