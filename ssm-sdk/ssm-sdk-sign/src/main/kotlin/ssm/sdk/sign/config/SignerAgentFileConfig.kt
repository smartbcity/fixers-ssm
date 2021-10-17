package ssm.sdk.sign.config

import ssm.sdk.sign.model.Signer
import ssm.sdk.sign.model.SignerAdmin

class SignerAdminFileConfig(
	val admin: SignerAgentFileConfig,
)
class SignerUserFileConfig(
	val user: SignerAgentFileConfig,
)

class SignerAgentFileConfig(
	val name: String,
	val key: String,
)

fun SignerAdminFileConfig.adminSigner(): SignerAdmin {
	return SignerAdmin.loadFromFile(admin.name, admin.key)
}

fun SignerUserFileConfig.signer(): Signer {
	return Signer.loadFromFile(user.name, user.key)
}
