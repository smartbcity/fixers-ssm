package ssm.sdk.sign.model

import java.security.KeyPair
import ssm.sdk.sign.crypto.KeyPairReader

class SignerAdmin(name: String, pair: KeyPair) : Signer(name, pair) {
	constructor(signer: Signer) : this(signer.name, signer.pair)

	companion object {
		@Throws(Exception::class)
		fun loadFromFile(filename: String): SignerAdmin {
			val keypair = KeyPairReader.loadKeyPair(filename)
			return SignerAdmin(filename, keypair)
		}

		@Throws(Exception::class)
		fun loadFromFile(name: String, filename: String?): SignerAdmin {
			val keypair = KeyPairReader.loadKeyPair(filename!!)
			return SignerAdmin(name, keypair)
		}
	}
}
