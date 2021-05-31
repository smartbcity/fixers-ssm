package x2.api.ssm.api

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ssm.coucbdb.dsl.query.CdbGetSsmListQueryFunction
import ssm.dsl.query.SsmGetQueryFunction
import ssm.dsl.query.SsmGetSessionQueryFunction
import ssm.dsl.query.SsmListSessionQueryFunction
import ssm.dsl.query.SsmListSsmQueryFunction

@Service
class SsmApiFinderService(
	private val cdbGetSsmListQueryFunction: CdbGetSsmListQueryFunction,
	private val ssmGetQueryFunction: SsmGetQueryFunction,
	private val ssmGetSessionQueryFunction: SsmGetSessionQueryFunction,
	private val ssmListSsmQueryFunction: SsmListSsmQueryFunction,
	private val ssmListSessionQueryFunction: SsmListSessionQueryFunction
) {
	@Bean
	fun getAllSsm() = cdbGetSsmListQueryFunction

	@Bean
	fun getAllSsmIds() = ssmListSsmQueryFunction

	@Bean
	fun getSsm() = ssmGetQueryFunction

	@Bean
	fun getAllSessionIds() = ssmListSessionQueryFunction

	@Bean
	fun getSession() = ssmGetSessionQueryFunction
}
