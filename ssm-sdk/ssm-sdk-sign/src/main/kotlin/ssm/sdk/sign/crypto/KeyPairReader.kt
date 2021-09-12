package ssm.sdk.sign.crypto

import java.io.IOException
import java.security.GeneralSecurityException
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.InvalidKeySpecException
import java.security.spec.RSAPrivateCrtKeySpec
import java.security.spec.X509EncodedKeySpec
import org.bouncycastle.asn1.pkcs.RSAPrivateKey
import org.bouncycastle.crypto.CryptoException
import org.bouncycastle.util.io.pem.PemObject
import org.bouncycastle.util.io.pem.PemReader
import ssm.sdk.sign.utils.FileUtils

object KeyPairReader {

	private const val BUFFER = 2048
	private const val ERROR_MESSAGE = "Private key can't be loaded"
	private const val RSA_KEY = "RSA"

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
			val kf = KeyFactory.getInstance(RSA_KEY)
			val privSpec = RSAPrivateCrtKeySpec(key.modulus,
				key.publicExponent,
				key.privateExponent,
				key.prime1,
				key.prime2,
				key.exponent1,
				key.exponent2,
				key.coefficient)
			return kf.generatePrivate(privSpec)
		} catch (e: GeneralSecurityException) {
			throw CryptoException(ERROR_MESSAGE, e)
		} catch (e: IOException) {
			throw CryptoException(ERROR_MESSAGE, e)
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
			val kf = KeyFactory.getInstance(RSA_KEY)
			return kf.generatePublic(pubSpec)
		} catch (e: GeneralSecurityException) {
			throw CryptoException(ERROR_MESSAGE, e)
		} catch (e: IOException) {
			throw CryptoException(ERROR_MESSAGE, e)
		}
	}

	@Throws(CryptoException::class)
	fun fromByteArray(pub: ByteArray?): PublicKey {
		try {
			return KeyFactory.getInstance(RSA_KEY).generatePublic(X509EncodedKeySpec(pub))
		} catch (e: InvalidKeySpecException) {
			throw CryptoException(ERROR_MESSAGE, e)
		} catch (e: NoSuchAlgorithmException) {
			throw CryptoException(ERROR_MESSAGE, e)
		}
	}

	@Throws(CryptoException::class)
	fun generateRSAKey(): KeyPair {
		try {

			val kpg = KeyPairGenerator.getInstance(RSA_KEY)
			kpg.initialize(BUFFER)
			return kpg.generateKeyPair()
		} catch (e: NoSuchAlgorithmException) {
			throw CryptoException(ERROR_MESSAGE, e)
		}
	}

	@Throws(IOException::class)
	private fun getPemObject(filename: String): PemObject {
		val reader = FileUtils.getReader(filename)
		val rpem = PemReader(reader)
		return rpem.readPemObject()
	}
}
