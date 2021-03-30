package ssm.client.crypto;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

import ssm.client.sign.crypto.KeyPairReader;
import ssm.client.sign.crypto.RSACipher;

class RSACipherTest {

    @Test
    void encrypt() throws Exception {
        PublicKey pubKey = KeyPairReader.loadPublicKey("command/vivi");
        String encrypted = RSACipher.encrypt("msg to encrypt".getBytes(), pubKey);
        Assertions.assertThat(encrypted).isNotEmpty();
    }


    @Test
    void decrypt() throws Exception {
        PublicKey pubKey = KeyPairReader.loadPublicKey("command/vivi");
        String encrypted = RSACipher.encrypt("msg to encrypt and decrypt".getBytes(), pubKey);


        PrivateKey privKey = KeyPairReader.loadPrivateKey("command/vivi");
        String value = RSACipher.decrypt(encrypted, privKey);
        Assertions.assertThat(value).isNotEmpty().isEqualTo("msg to encrypt and decrypt");
    }

}