package ssm.client.sign.model

import java.security.KeyPair

class SignerAdmin(name: String, pair: KeyPair): Signer(name, pair) {
    constructor(signer: Signer): this(signer.name, signer.pair)
}