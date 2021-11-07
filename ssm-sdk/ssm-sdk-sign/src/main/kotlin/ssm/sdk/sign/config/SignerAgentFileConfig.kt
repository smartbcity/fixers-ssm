package ssm.sdk.sign.config

import ssm.sdk.sign.model.SignerAdmin
import ssm.sdk.sign.model.SignerUser

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

fun SignerAdminFileConfig.signer(): SignerAdmin {
	return SignerAdmin.loadFromFile(admin.name, admin.key)
}

fun SignerUserFileConfig.signer(): SignerUser {
	return SignerUser.loadFromFile(user.name, user.key)
}
