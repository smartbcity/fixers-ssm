package ssm.couchdb.dsl.model

import kotlin.reflect.KClass
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmGrant
import ssm.chaincode.dsl.model.SsmSessionState

sealed class DocType<T : Any>(
	val docType: String,
	val clazz: KClass<T>,
) {
	object Admin : DocType<SsmAgent>("admin", SsmAgent::class)
	object User : DocType<SsmAgent>("user", SsmAgent::class)
	object Grant : DocType<SsmGrant>("grant", SsmGrant::class)
	object Ssm : DocType<ssm.chaincode.dsl.model.Ssm>("ssm", ssm.chaincode.dsl.model.Ssm::class)
	object State : DocType<SsmSessionState>("state", SsmSessionState::class)
}
