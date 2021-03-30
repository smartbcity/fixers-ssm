package ssm.client.sign.model

import ssm.client.sign.crypto.KeyPairReader
import java.security.KeyPair

class SignerAdmin(name: String, pair: KeyPair): Signer(name, pair) {
    constructor(signer: Signer): this(signer.name, signer.pair)

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