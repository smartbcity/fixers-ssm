package ssm.sdk.sign

import ssm.sdk.sign.model.Signer

interface SignerProvider {
	fun get(): Signer
}

class SignerProviderData(
	private val admin: Signer
): SignerProvider {
	override fun get() = admin
}
