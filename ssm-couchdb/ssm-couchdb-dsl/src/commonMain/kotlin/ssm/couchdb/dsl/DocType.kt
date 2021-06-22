package ssm.couchdb.dsl

import ssm.chaincode.dsl.SsmAgentBase
import ssm.chaincode.dsl.SsmBase
import ssm.chaincode.dsl.SsmGrant
import ssm.chaincode.dsl.SsmSessionStateBase
import kotlin.reflect.KClass

sealed class DocType<T: Any>(
	val docType: String,
	val clazz: KClass<T>,
) {
	object Admin: DocType<SsmAgentBase>("admin", SsmAgentBase::class)
	object User: DocType<SsmAgentBase>("user", SsmAgentBase::class)
	object Grant: DocType<SsmGrant>("grant", SsmGrant::class)
	object Ssm: DocType<SsmBase>("ssm", SsmBase::class)
	object State: DocType<SsmSessionStateBase>("state", SsmSessionStateBase::class)
}