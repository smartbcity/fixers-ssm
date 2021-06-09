package ssm.dsl

import kotlin.reflect.KClass

sealed class DocType<T: Any>(
	val docType: String,
	val clazz: KClass<T>,
) {
	object Admin: DocType<SsmAgentBase>("admin", SsmAgentBase::class)
	object User: DocType<SsmAgentBase>("user", SsmAgentBase::class)
	object Grant: DocType<SsmGrant>("grant", SsmGrant::class)
	object Ssm: DocType<ssm.dsl.SsmBase>("ssm", ssm.dsl.SsmBase::class)
	object State: DocType<SsmSessionStateBase>("state", SsmSessionStateBase::class)
}