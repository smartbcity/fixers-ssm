package ssm.dsl

import kotlin.reflect.KClass

sealed class DocType<T : Any>(
	val docType: String,
	val clazz: KClass<T>,
) {
	object Admin : DocType<SsmAgent>("admin", SsmAgent::class)
	object User : DocType<SsmAgent>("user", SsmAgent::class)
	object Grant : DocType<SsmGrant>("grant", SsmGrant::class)
	object Ssm : DocType<ssm.dsl.Ssm>("ssm", ssm.dsl.Ssm::class)
	object State : DocType<SsmSession>("state", SsmSession::class)
}