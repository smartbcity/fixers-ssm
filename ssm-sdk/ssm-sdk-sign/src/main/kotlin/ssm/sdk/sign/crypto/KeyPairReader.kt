package ssm.sdk.sign.crypto

import org.bouncycastle.asn1.pkcs.RSAPrivateKey
import org.bouncycastle.crypto.CryptoException
import org.bouncycastle.util.io.pem.PemObject
import org.bouncycastle.util.io.pem.PemReader
import ssm.sdk.sign.utils.FileUtils
import java.io.IOException
import java.security.*
import java.security.spec.InvalidKeySpecException
import java.security.spec.RSAPrivateCrtKeySpec
import java.security.spec.X509EncodedKeySpec

class KeyPairReader {
    companion object {
        @Throws(CryptoException::class)
        fun loadKeyPair(filename: String): KeyPair {
            val priv = loadPrivateKey(filename)
            val pub = loadPublicKey(filename)
            return KeyPair(pub, priv)
        }

        @Throws(CryptoException::class)
        fun loadPrivateKey(filename: String): PrivateKey {
            try {
                val pem = getPemObject(filename)
                val key = RSAPrivateKey.getInstance(pem.content)
                val kf = KeyFactory.getInstance("RSA")
                val privSpec = RSAPrivateCrtKeySpec(key.modulus, key.publicExponent, key.privateExponent, key.prime1, key.prime2, key.exponent1, key.exponent2, key.coefficient)
                return kf.generatePrivate(privSpec)
            } catch (e: InvalidKeySpecException) {
                throw CryptoException("Private key can't be loaded", e)
            } catch (e: NoSuchAlgorithmException) {
                throw CryptoException("Private key can't be loaded", e)
            } catch (e: IOException) {
                throw CryptoException("Private key can't be loaded", e)
            }
        }

        @Throws(CryptoException::class)
        fun loadPublicKey(filename: String): PublicKey {
            try {
                var fixedFileName = filename
                if (!filename.endsWith(".pub")) {
                    fixedFileName = "$filename.pub"
                }

                val pem = getPemObject(fixedFileName)
                val pubKeyBytes = pem.content
                val pubSpec = X509EncodedKeySpec(pubKeyBytes)
                val kf = KeyFactory.getInstance("RSA")
                return kf.generatePublic(pubSpec)
            } catch (e: InvalidKeySpecException) {
                throw CryptoException("Public key can't be loaded", e)
            } catch (e: NoSuchAlgorithmException) {
                throw CryptoException("Public key can't be loaded", e)
            } catch (e: IOException) {
                throw CryptoException("Public key can't be loaded", e)
            }
        }

        @Throws(CryptoException::class)
        fun fromByteArray(pub: ByteArray?): PublicKey {
            try {
                return KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(pub))
            } catch (e: InvalidKeySpecException) {
                throw CryptoException("Public key can't be loaded", e)
            } catch (e: NoSuchAlgorithmException) {
                throw CryptoException("Public key can't be loaded", e)
            }
        }

        @Throws(CryptoException::class)
        fun generateRSAKey(): KeyPair {
            try {
                val kpg = KeyPairGenerator.getInstance("RSA")
                kpg.initialize(2048)
                return kpg.generateKeyPair()
            } catch (e: NoSuchAlgorithmException) {
                throw CryptoException("RSA key can't be generated", e)
            }
        }

        @Throws(IOException::class)
        private fun getPemObject(filename: String): PemObject {
            val reader = FileUtils.getReader(filename)
            val rpem = PemReader(reader)
            return rpem.readPemObject()
        }
    }
}