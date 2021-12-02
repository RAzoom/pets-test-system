package razoom.utils.jackson.encrypt;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CipherLongDeserializer extends JsonDeserializer<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CipherLongDeserializer.class);

    private Cipher decipher = null;

    public CipherLongDeserializer() {
        try {
            decipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(Configure.KEY_BYTES, Configure.ALGORITHM);
            decipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Long result = null;
        try {
            String valueAsString = jsonParser.getValueAsString();
            byte[] jsonValue = Base64.getDecoder().decode(valueAsString);
            jsonValue = decipher.doFinal(jsonValue);
            result = Long.parseLong(new String(jsonValue));
        } catch (IllegalBlockSizeException | IllegalArgumentException | BadPaddingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}
