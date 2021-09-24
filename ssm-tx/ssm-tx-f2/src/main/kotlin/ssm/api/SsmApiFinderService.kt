package ssm.api

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ssm.api.model.toTxSession
import ssm.api.model.toTxSsm
import ssm.chaincode.dsl.SsmChaincodeProperties
import ssm.chaincode.dsl.SsmSessionStateDTO
import ssm.chaincode.dsl.SsmSessionStateLog
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.query.SsmGetQuery
import ssm.chaincode.dsl.query.SsmGetQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionQuery
import ssm.chaincode.dsl.query.SsmGetSessionQueryFunction
import ssm.chaincode.dsl.query.SsmGetTransactionQuery
import ssm.chaincode.dsl.query.SsmGetTransactionQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQuery
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQuery
import ssm.couchdb.f2.query.CouchdbSsmListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmSessionStateListQueryFunctionImpl
import ssm.tx.autoconfiguration.SsmConfigProperties
import ssm.tx.dsl.SsmApiFinder
import ssm.tx.dsl.config.TxSsmLocationProperties
import ssm.tx.dsl.features.query.TxQueryDTO
import ssm.tx.dsl.features.query.TxSsmGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmGetQueryResult
import ssm.tx.dsl.features.query.TxSsmListQueryFunction
import ssm.tx.dsl.features.query.TxSsmListQueryResult
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryResult
import ssm.tx.dsl.features.query.TxSsmSessionListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionListQueryResult
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryResult
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryResult
import ssm.tx.dsl.model.TxSsmSession
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionState

@Service
class SsmApiFinderService(
//	private val couchdbSsmSessionListQueryFunction: CouchdbSsmSessionStateListQueryFunction,
//	private val couchdbSsmListQueryFunction: CouchdbSsmListQueryFunction,
	private val ssmGetQueryFunction: SsmGetQueryFunction,
	private val ssmGetSessionQueryFunction: SsmGetSessionQueryFunction,
	private val ssmGetSessionLogsQueryFunction: SsmGetSessionLogsQueryFunction,
	private val ssmGetTransactionQueryFunction: SsmGetTransactionQueryFunction,
	private val ssmConfigProperties: SsmConfigProperties,
) : SsmApiFinder {

	@Bean
	override fun txSsmListQueryFunction(): TxSsmListQueryFunction = f2Function {
		val ssmNames = ssmConfigProperties.list.keys
		ssmConfigProperties.list
			.flatMap { (_, ssmConfigs) -> ssmConfigs.values }
			.distinctBy { ssmConfig -> ssmConfig.channelId to ssmConfig.chaincodeId }
			.asFlow()
			.flatMapConcat { config ->
				CouchdbSsmListQueryFunctionImpl(config.couchdb)
					.couchdbSsmListQueryFunction()
					.invoke(
						CouchdbSsmListQuery(
							chaincodeId = config.chaincodeId,
							channelId = config.channelId,
							pagination = null
						)
					).page.list.asFlow()
			}.filter { result ->
				result.name in ssmNames
			}.map { result ->
				result.toTxSsm()
			}.let {
				val txSsmListQueryResult = TxSsmListQueryResult(it.toList())
				txSsmListQueryResult
			}
	}

	@Bean
	override fun txSsmGetQueryFunction(): TxSsmGetQueryFunction = f2Function { cmd ->
		val config = getConfig(cmd)

		val command = SsmGetQuery(
			name = cmd.ssm,
			chaincode = config.toChaincodeProperties(),
			bearerToken = cmd.bearerToken
		)

		ssmGetQueryFunction(command)
			.ssm
			?.toTxSsm()
			.let(::TxSsmGetQueryResult)
	}

	@Bean
	override fun txSsmSessionListQueryFunction(): TxSsmSessionListQueryFunction = f2Function { cmd ->
		val config = getConfig(cmd)
		val query = CouchdbSsmSessionStateListQuery(
			channelId = config.channelId,
			chaincodeId = config.chaincodeId,
			ssm = cmd.ssm
		)
		CouchdbSsmSessionStateListQueryFunctionImpl(config.couchdb).couchdbSsmSessionStateListQueryFunction().invoke(
			query
		).page.list
			.filter { sessionState -> sessionState.session.isNotBlank() }
			.map { sessionState -> sessionState.toTxSession(config, cmd.bearerToken) }.let {
				TxSsmSessionListQueryResult(it)
			}
	}

	@Bean
	override fun txSsmSessionGetQueryFunction(): TxSsmSessionGetQueryFunction = f2Function { cmd ->
		val config = getConfig(cmd)
		val sessionQuery = SsmGetSessionQuery(
			name = cmd.sessionId,
			chaincode = config.toChaincodeProperties(),
			bearerToken = cmd.bearerToken
		)

		try {
			ssmGetSessionQueryFunction(sessionQuery)
				.session
				?.toTxSession(config, cmd.bearerToken).let {
					TxSsmSessionGetQueryResult(
						it
					)
				}
		} catch (e: Exception) {
			e.printStackTrace()
			TxSsmSessionGetQueryResult(null)
		}
	}

	private fun getConfig(cmd: TxQueryDTO): TxSsmLocationProperties {
		return ssmConfigProperties.list[cmd.ssm]?.entries?.first()?.value
			?: throw IllegalArgumentException("Configuration of SSM [${cmd.ssm}] not found")
	}

	@Bean
	override fun txSsmSessionLogListQueryFunction(): TxSsmSessionLogListQueryFunction = f2Function { cmd ->
		val config = getConfig(cmd)
		val logs = getSessionLogs(cmd.sessionId, config, cmd.bearerToken)

		logs.map { log ->
			val transaction = getTransaction(log.txId, config, cmd.bearerToken)
			TxSsmSessionState(
				details = log.state,
				transaction = transaction
			)
		}.let {
			TxSsmSessionLogListQueryResult(it)
		}
	}

	@Bean
	override fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryFunction = f2Function { cmd ->
		val config = getConfig(cmd)
		val logs = getSessionLogs(cmd.sessionId, config, cmd.bearerToken)
		val transaction = getTransaction(cmd.txId, config, cmd.bearerToken)
		logs.firstOrNull { log -> log.txId == cmd.txId }
			?.let { sessionState ->
				TxSsmSessionState(
					details = sessionState.state,
					transaction = transaction
				)
			}.let {
				TxSsmSessionLogGetQueryResult(it)
			}
	}

	private suspend fun getSessionLogs(
		session: TxSsmSessionId,
		ssmConfig: TxSsmLocationProperties,
		bearerToken: String?,
	): List<SsmSessionStateLog> {
		val query = SsmGetSessionLogsQuery(
			session = session,
			chaincode = ssmConfig.toChaincodeProperties(),
			bearerToken = bearerToken
		)

		return try {
			ssmGetSessionLogsQueryFunction(query).logs
		} catch (e: Exception) {
			e.printStackTrace()
			emptyList()
		}
	}

	private suspend fun getTransaction(
		id: TransactionId?,
		ssmConfig: TxSsmLocationProperties,
		bearerToken: String?,
	): Transaction? {
		return id?.let {
			val query = SsmGetTransactionQuery(
				id = id,
				chaincode = ssmConfig.toChaincodeProperties(),
				bearerToken = bearerToken
			)
			ssmGetTransactionQueryFunction(query).transaction
		}
	}

	private suspend fun SsmSessionStateDTO.toTxSession(
		ssmConfig: TxSsmLocationProperties,
		bearerToken: String?,
	): TxSsmSession {
		val sessionLogs = getSessionLogs(session, ssmConfig, bearerToken)

		val firstTransactionId = sessionLogs.minByOrNull { sessionLog -> sessionLog.state.iteration }?.txId
		val lastTransactionId = sessionLogs.maxByOrNull { sessionLog -> sessionLog.state.iteration }?.txId

		val firstTransaction = getTransaction(firstTransactionId, ssmConfig, bearerToken)
		val lastTransaction = getTransaction(lastTransactionId, ssmConfig, bearerToken)

		return this.toTxSession(firstTransaction, lastTransaction)
	}

	private fun TxSsmLocationProperties.toChaincodeProperties(): SsmChaincodeProperties {
		TODO()
//		return ssmConfigProperties.chaincode[chaincode]
//			?: throw ConfigDataLocationNotFoundException(ConfigDataLocation.of("ssm.chaincode.$chaincode"))
	}
}
