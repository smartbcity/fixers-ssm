package ssm.api

import f2.function.spring.adapter.f2Function
import f2.function.spring.invokeSingle
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ssm.tx.dsl.config.TxSsmLocationProperties
import ssm.tx.dsl.SsmApiFinder
import ssm.tx.dsl.model.TxSsmSession
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionState
import ssm.api.model.toTxSession
import ssm.api.model.toTxSsm
import ssm.chaincode.dsl.SsmBase
import ssm.chaincode.dsl.SsmSessionStateBase
import ssm.chaincode.dsl.SsmSessionStateLog
import ssm.chaincode.dsl.blockchain.TransactionBase
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.query.*
import ssm.chaincode.dsl.query.SsmGetQuery
import ssm.chaincode.dsl.query.SsmGetQueryFunction
import ssm.couchdb.dsl.query.*
import ssm.tx.dsl.config.TxSsmConfig
import ssm.tx.dsl.features.query.*

@Service
class SsmApiFinderService(
	private val cdbGetSsmSessionListQueryFunction: CdbGetSsmSessionListQueryFunction,
	private val cdbGetSsmListQueryFunction: CdbGetSsmListQueryFunction,
	private val ssmGetQueryFunction: SsmGetQueryFunction,
	private val ssmGetSessionQueryFunction: SsmGetSessionQueryFunction,
	private val ssmGetSessionLogsQueryFunction: SsmGetSessionLogsQueryFunction,
	private val ssmGetTransactionQueryFunction: SsmGetTransactionQueryFunction,
	private val txSsmConfig: TxSsmConfig
): SsmApiFinder {

	@Bean
	override fun txSsmListQueryFunction(): TxSsmListQueryFunction = f2Function { _ ->
		val commands = txSsmConfig.values.flatMap { ssmConfigs ->
			ssmConfigs.map { (_, ssmConfig) ->
				CdbGetSsmListQuery(
					dbConfig = ssmConfig.couchdb,
					dbName = ssmConfig.dbName
				)
			}
		}.asFlow()

		cdbGetSsmListQueryFunction(commands).toList()
			.flatMap(CdbGetSsmListQueryResult::ssmList)
			.map(SsmBase::toTxSsm)
			.let{
				TxSsmListQueryResult(it)
			}
	}

	@Bean
	override fun txSsmGetOneQueryFunction(): TxSsmGetQueryFunction = f2Function { cmd ->
		val config = getConfig(cmd)

		val command = SsmGetQuery(
			name = cmd.ssm,
			baseUrl = config.baseUrl,
			channelId = config.channel,
			chaincodeId = config.chaincode,
			bearerToken = cmd.bearerToken
		)

		ssmGetQueryFunction.invokeSingle(command)
			.ssmBase
			?.toTxSsm()
			.let(::TxSsmGetQueryResult)
	}

	@Bean
	override fun txSsmSessionGetListQueryFunction(): TxSsmSessionListQueryFunction = f2Function { cmd ->
		val config = getConfig(cmd)
		val command = CdbGetSsmSessionListQuery(
			dbConfig = config.couchdb,
			dbName = config.dbName,
			ssm = cmd.ssm
		)
		cdbGetSsmSessionListQueryFunction.invokeSingle(command)
			.sessions
			.filter { sessionState -> sessionState.session.isNotBlank() }
			.map { sessionState -> sessionState.toTxSession(config, cmd.bearerToken) }.let {
				TxSsmSessionListQueryResult(it)
			}
	}

	@Bean
	override fun txSsmSessionGetOneQueryFunction(): TxSsmSessionGetOneQueryFunction = f2Function { cmd ->
		val config = getConfig(cmd)
		val sessionQuery = SsmGetSessionQuery(
			name = cmd.sessionId,
			baseUrl = config.baseUrl,
			channelId = config.channel,
			chaincodeId = config.chaincode,
			bearerToken = cmd.bearerToken
		)

		try {
			ssmGetSessionQueryFunction.invokeSingle(sessionQuery)
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
		return txSsmConfig[cmd.ssm]?.entries?.first()?.value
			?: throw IllegalArgumentException("Configuration of SSM [${cmd.ssm}] not found")
	}

	@Bean
	override fun txSsmSessionLogGetListQueryFunction(): TxSsmSessionLogListQueryFunction = f2Function { cmd ->
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
			baseUrl = ssmConfig.baseUrl,
			channelId = ssmConfig.channel,
			chaincodeId = ssmConfig.chaincode,
			bearerToken = bearerToken
		)

		return try {
			ssmGetSessionLogsQueryFunction.invokeSingle(query).logs
		} catch (e: Exception) {
			e.printStackTrace()
			emptyList()
		}
	}

	private suspend fun getTransaction(id: TransactionId?, ssmConfig: TxSsmLocationProperties, bearerToken: String?): TransactionBase? {
		return id?.let {
			val query = SsmGetTransactionQuery(
				id = id,
				baseUrl = ssmConfig.baseUrl,
				channelId = ssmConfig.channel,
				chaincodeId = ssmConfig.chaincode,
				bearerToken = bearerToken
			)
			ssmGetTransactionQueryFunction.invokeSingle(query).transaction
		}
	}

	private suspend fun SsmSessionStateBase.toTxSession(ssmConfig: TxSsmLocationProperties, bearerToken: String?): TxSsmSession {
		val sessionLogs = getSessionLogs(session, ssmConfig, bearerToken)

		val firstTransactionId = sessionLogs.minByOrNull { sessionLog -> sessionLog.state.iteration }?.txId
		val lastTransactionId = sessionLogs.maxByOrNull { sessionLog -> sessionLog.state.iteration }?.txId

		val firstTransaction = getTransaction(firstTransactionId, ssmConfig, bearerToken)
		val lastTransaction = getTransaction(lastTransactionId, ssmConfig, bearerToken)

		return this.toTxSession(firstTransaction, lastTransaction)
	}

}
