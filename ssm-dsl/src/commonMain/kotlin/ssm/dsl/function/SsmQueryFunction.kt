package ssm.dsl.function

import ssm.dsl.query.*

interface SsmQueryFunction {
	fun getSsm(): GetSsmFunction
	fun getSsmSession(): GetSsmSessionFunction
	fun getSsmUser(): GetSsmUserFunction
	fun getSsmAdmin(): GetSsmAdminFunction

	fun getListAdmin(): GetListAdminQueryFunction
	fun getListUser(): GetListUserQueryFunction
	fun getListSsm(): GetListSsmQueryFunction
	fun getListSsmSession(): GetListSsmSessionQueryFunction
}

interface SsmQueryRemoteFunction {
	fun getSsm(): GetSsmRemoteFunction
	fun getSsmSession(): GetSsmSessionRemoteFunction
	fun getSsmUser(): GetSsmUserRemoteFunction
	fun getSsmAdmin(): GetSsmAdminRemoteFunction

	fun getListAdmin(): GetListAdminQueryRemoteFunction
	fun getListUser(): GetListUserQueryRemoteFunction
	fun getListSsm(): GetListSsmQueryRemoteFunction
	fun getListSsmSession(): GetListSsmSessionRemoteQueryFunction
}
