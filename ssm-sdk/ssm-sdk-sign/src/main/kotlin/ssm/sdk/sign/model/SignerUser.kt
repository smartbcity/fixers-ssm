package ssm.sdk.sign.model

import java.security.KeyPair
import ssm.sdk.sign.crypto.KeyPairReader

data class SignerUser(
	override val name: SignerName,
	override val pair: KeyPair
) : Signer {

	companion object {
		@Throws(Exception::class)
		fun loadFromFile(filename: String): SignerUser {
			val keypair = KeyPairReader.loadKeyPair(filename)
			return SignerUser(filename, keypair)
		}

		@Throws(Exception::class)
		fun loadFromFile(name: SignerName, filename: String?): SignerUser {
			val keypair = KeyPairReader.loadKeyPair(filename!!)
			return SignerUser(name, keypair)
		}

		@Throws(Exception::class)
		fun generate(name: SignerName): SignerUser {
			val keypair = KeyPairReader.generateRSAKey()
			return SignerUser(name, keypair)
		}
	}
}
