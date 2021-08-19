package ssm.api

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import org.springframework.boot.context.config.ConfigDataLocation
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ssm.api.model.toTxSession
import ssm.api.model.toTxSsm
import ssm.chaincode.dsl.Ssm
import ssm.chaincode.dsl.SsmChaincodeProperties
import ssm.chaincode.dsl.SsmSessionState
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
import ssm.couchdb.dsl.query.CdbSsmListQuery
import ssm.couchdb.dsl.query.CdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CdbSsmListQueryResultDTO
import ssm.couchdb.dsl.query.CdbSsmSessionListQuery
import ssm.couchdb.dsl.query.CdbSsmSessionListQueryFunction
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
	private val cdbSsmSessionListQueryFunction: CdbSsmSessionListQueryFunction,
	private val cdbSsmListQueryFunction: CdbSsmListQueryFunction,
	private val ssmGetQueryFunction: SsmGetQueryFunction,
	private val ssmGetSessionQueryFunction: SsmGetSessionQueryFunction,
	private val ssmGetSessionLogsQueryFunction: SsmGetSessionLogsQueryFunction,
	private val ssmGetTransactionQueryFunction: SsmGetTransactionQueryFunction,
	private val ssmConfigProperties: SsmConfigProperties
): SsmApiFinder {

	@Bean
	override fun txSsmListQueryFunction(): TxSsmListQueryFunction = f2Function { _ ->
		val commands = ssmConfigProperties.list.flatMap { (_, ssmConfigs) ->
			ssmConfigs.map { (_, ssmConfig) ->
				CdbSsmListQuery(
					dbConfig = ssmConfig.couchdb,
					dbName = ssmConfig.dbName
				)
			}
		}

		cdbSsmListQueryFunction(commands.asFlow()).toList()
			.flatMap(CdbSsmListQueryResultDTO::ssmList)
			.map(Ssm::toTxSsm)
			.let(::TxSsmListQueryResult)
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
		val command = CdbSsmSessionListQuery(
			dbConfig = config.couchdb,

			dbName = config.dbName,
			ssm = cmd.ssm
		)
		cdbSsmSessionListQueryFunction(command)
			.sessions
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

	private suspend fun getSessionLogs(session: TxSsmSessionId, ssmConfig: TxSsmLocationProperties, bearerToken: String?): List<SsmSessionStateLog> {
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

	private suspend fun getTransaction(id: TransactionId?, ssmConfig: TxSsmLocationProperties, bearerToken: String?): Transaction? {
		return id?.let {
			val query = SsmGetTransactionQuery(
				id = id,
				chaincode = ssmConfig.toChaincodeProperties(),
				bearerToken = bearerToken
			)
			ssmGetTransactionQueryFunction(query).transaction
		}
	}

	private suspend fun SsmSessionState.toTxSession(ssmConfig: TxSsmLocationProperties, bearerToken: String?): TxSsmSession {
		val sessionLogs = getSessionLogs(session, ssmConfig, bearerToken)

		val firstTransactionId = sessionLogs.minByOrNull { sessionLog -> sessionLog.state.iteration }?.txId
		val lastTransactionId = sessionLogs.maxByOrNull { sessionLog -> sessionLog.state.iteration }?.txId

		val firstTransaction = getTransaction(firstTransactionId, ssmConfig, bearerToken)
		val lastTransaction = getTransaction(lastTransactionId, ssmConfig, bearerToken)

		return this.toTxSession(firstTransaction, lastTransaction)
	}

	private fun TxSsmLocationProperties.toChaincodeProperties(): SsmChaincodeProperties {
		return ssmConfigProperties.chaincode[chaincode]
			?: throw ConfigDataLocationNotFoundException(ConfigDataLocation.of("ssm.chaincode.$chaincode"))
	}
}
