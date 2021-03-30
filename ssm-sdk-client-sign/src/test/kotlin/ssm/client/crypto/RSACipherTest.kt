package ssm.client.crypto

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.client.sign.crypto.KeyPairReader
import ssm.client.sign.crypto.RSACipher

internal class RSACipherTest {
    @Test
    @Throws(Exception::class)
    fun encrypt() {
        val pubKey = KeyPairReader.loadPublicKey("command/vivi")
        val encrypted: String = RSACipher.encrypt("msg to encrypt".toByteArray(), pubKey)
        Assertions.assertThat(encrypted).isNotEmpty
    }

    @Test
    @Throws(Exception::class)
    fun decrypt() {
        val pubKey = KeyPairReader.loadPublicKey("command/vivi")
        val encrypted: String = RSACipher.encrypt("msg to encrypt and decrypt".toByteArray(), pubKey)
        val privKey = KeyPairReader.loadPrivateKey("command/vivi")
        val value: String = RSACipher.decrypt(encrypted, privKey)
        Assertions.assertThat(value).isNotEmpty.isEqualTo("msg to encrypt and decrypt")
    }
}