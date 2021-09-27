package ssm.chaincode.dsl.model

import ssm.chaincode.dsl.model.CreditDTO

actual interface SsmGrantDTO {
	actual val user: String
	actual val iteration: Int
	actual val credits: Map<String, CreditDTO>
}

actual interface CreditDTO {
	actual val amount: Int
}
