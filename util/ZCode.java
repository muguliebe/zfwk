package zany.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZCode {

    StandardPBEStringEncryptor pbeEnc;

    ZCode(@Value("${encrypt.key}") String key) {
        pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
    }

    public String decrypt(String strDes) {
        return pbeEnc.decrypt(strDes);
    }

    public String encrypt(String strDes) {
        return pbeEnc.encrypt(strDes);
    }
}
