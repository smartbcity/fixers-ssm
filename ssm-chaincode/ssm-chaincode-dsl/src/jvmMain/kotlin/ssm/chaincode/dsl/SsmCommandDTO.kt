package ssm.chaincode.dsl

import f2.dsl.cqrs.Command

actual interface SsmCommandDTO: Command {
	actual val chaincode: SsmChaincodePropertiesDTO
	actual val bearerToken: String?
}