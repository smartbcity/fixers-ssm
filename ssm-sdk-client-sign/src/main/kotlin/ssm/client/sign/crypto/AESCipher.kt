package ssm.client.sign.crypto

import com.google.common.io.ByteStreams
import org.bouncycastle.crypto.CryptoException
import java.io.*
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.*
import javax.crypto.*
import javax.crypto.spec.SecretKeySpec

class AESCipher {
    companion object {
        private const val ALGO = "AES"

        @Throws(NoSuchAlgorithmException::class)
        fun generateSecretKey(): SecretKey {
            val kg = KeyGenerator.getInstance(ALGO)
            kg.init(SecureRandom(byteArrayOf(0x00.toByte(), 0x01.toByte(), 0x02.toByte())))
            return kg.generateKey()
        }

        fun secretKeyFromBase64(b64Key: String?): SecretKey {
            val key = Base64.getDecoder().decode(b64Key)
            return SecretKeySpec(key, ALGO)
        }

        @Throws(CryptoException::class)
        fun decrypt(fileInput: InputStream, key: SecretKey): InputStream {
            try {
                return getDecryptCipher(key, fileInput)
            } catch (e: Exception) {
                throw CryptoException("Error decrypting file", e)
            }
        }

        @Throws(CryptoException::class)
        fun encrypt(file: File, outputStream: OutputStream?, key: SecretKey?) {
            var fileInput: FileInputStream? = null
            try {
                fileInput = FileInputStream(file)
                encrypt(fileInput, outputStream, key)
            } catch (e: Exception) {
                throw CryptoException("Error encrypting file:" + file.name, e)
            } finally {
                try {
                    fileInput?.close()
                } catch (e: IOException) {
                }
            }
        }

        @Throws(CryptoException::class)
        fun encrypt(fileInput: InputStream?, outputStream: OutputStream?, key: SecretKey?) {
            var output: OutputStream? = null
            try {
                output = getEncryptCipher(outputStream, key)
                ByteStreams.copy(fileInput!!, output)
            } catch (e: Exception) {
                throw CryptoException("Error encrypting:", e)
            } finally {
                try {
                    output?.close()
                } catch (e: IOException) {
                }
            }
        }

        @Throws(CryptoException::class)
        private fun getDecryptCipher(key: SecretKey, fileInput: InputStream): CipherInputStream {
            try {
                val cipher = Cipher.getInstance(ALGO)
                cipher.init(Cipher.DECRYPT_MODE, key)
                return CipherInputStream(fileInput, cipher)
            } catch (e: Exception) {
                throw CryptoException("Error decrypting", e)
            }
        }

        @Throws(CryptoException::class)
        private fun getEncryptCipher(fileOutput: OutputStream?, key: SecretKey?): CipherOutputStream {
            try {
                val cipher = Cipher.getInstance(ALGO)
                cipher.init(Cipher.ENCRYPT_MODE, key)
                return CipherOutputStream(fileOutput, cipher)
            } catch (e: Exception) {
                throw CryptoException("Error encrypting", e)
            }
        }
    }
}