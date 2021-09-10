package ssm.couchdb.dsl

import kotlin.reflect.KClass
import ssm.chaincode.dsl.SsmAgent
import ssm.chaincode.dsl.SsmGrant
import ssm.chaincode.dsl.SsmSessionState

sealed class DocType<T : Any>(
	val docType: String,
	val clazz: KClass<T>,
) {
	object Admin : DocType<SsmAgent>("admin", SsmAgent::class)
	object User : DocType<SsmAgent>("user", SsmAgent::class)
	object Grant : DocType<SsmGrant>("grant", SsmGrant::class)
	object Ssm : DocType<ssm.chaincode.dsl.Ssm>("ssm", ssm.chaincode.dsl.Ssm::class)
	object State : DocType<SsmSessionState>("state", SsmSessionState::class)
}
