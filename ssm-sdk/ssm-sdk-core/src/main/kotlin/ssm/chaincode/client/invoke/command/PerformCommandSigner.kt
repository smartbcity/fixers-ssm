package ssm.chaincode.client.invoke.command

import ssm.chaincode.dsl.model.SsmContextDTO
import ssm.sdk.sign.model.Signer

// {
//    "InvokeArgs": [
//        "perform",
//        "Sell",
//        "{\"session\":\"deal20181201\",\"public\":\"100 dollars 1978 Camaro\",\"iteration\":0}",
//        "sam",
//        "ANOVTS1mqW2M+u7IoR/A/S3lY2xnj7yS8fJg0k0XE3DeY+i23LzJL1ABrm/CxHbndqVtvmsDQ0pLJ/XGmdAxhpTSAj+oIi+bnQAxW5fAqtLt9KHOElnG7KWzO8xitHh7NaIDgMbMjxJ5tj8xRFB2OD69P6aqtv9sj6TqkIhWNCMYPzDl+/Rck3Su7/51heDeTkDjtPxnkyOYTnSTJF7eFaMTyauqAqtjQwznL9xhKIlxMcmLxmboEDNQd8tv3mT/8ALGhmo1YUWtMkgJ00li3NDhjq1+gVNjAcUpBhwN/N8lUmN6MElc9qwliHVOsWkwHAYvZ7r6Zdvf6typbFqkeA=="
//    ]
// }
//    echo "Usage: perform <action> <context> <signer>"
class PerformCommandSigner(signer: Signer?, private val action: String, context: SsmContextDTO?) :
	CommandSigner<SsmContextDTO?>(signer!!, COMMAND_NAME, context) {
	@Throws(Exception::class)
	override fun valueToSign(json: String): String {
		return action + json
	}

	override fun buildArgs(command: String, json: String, signer: String, b64Signature: String): InvokeArgs {
		return InvokeArgs(command, listOf(action, json, signer, b64Signature))
	}

	companion object {
		private const val COMMAND_NAME = "perform"
	}
}
