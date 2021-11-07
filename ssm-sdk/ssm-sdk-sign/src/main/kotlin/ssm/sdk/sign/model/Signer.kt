package ssm.sdk.sign.model

import java.security.KeyPair

typealias SignerName = String

interface Signer {
	val name: SignerName
	val pair: KeyPair
}
