package ssm.sdk.client

import com.fasterxml.jackson.core.type.TypeReference
import org.slf4j.LoggerFactory
import ssm.sdk.client.invoke.builder.HasGet
import ssm.sdk.client.invoke.builder.HasList
import ssm.sdk.client.ktor.KtorRepository
import ssm.sdk.client.model.InvokeReturn
import ssm.sdk.client.model.SsmCmdSigned
import ssm.sdk.client.model.buildArgs
import ssm.sdk.json.JSONConverter
import ssm.sdk.json.JsonUtils

class SsmRequester(
	private val channelId: String?,
	private val chaincodeId: String?,
	private val jsonConverter: JSONConverter,
	private val coopRepository: KtorRepository,
) {

	private val logger = LoggerFactory.getLogger(SsmRequester::class.java)

	suspend fun <T> log(value: String, query: HasGet, clazz: TypeReference<List<T>>): List<T> {
		val args = query.queryArgs(value)
		val request = coopRepository.query(
			cmd = QUERY,
			fcn = args.fcn,
			args = args.args,
			channelId = channelId,
			chaincodeId = chaincodeId,
		)
		logger.info(
			"Query the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}",
			channelId,
			chaincodeId, args.fcn, args.args
		)
		return request.let {
			JsonUtils.mapper.readValue(it.toByteArray(), clazz)
		}
	}

	suspend fun <T> query(value: String, query: HasGet, clazz: Class<T>): T? {
		val args = query.queryArgs(value)
		val request = coopRepository.query(
			cmd = QUERY,
			fcn = args.fcn,
			args = args.args,
			channelId = channelId,
			chaincodeId = chaincodeId,
		)
		logger.info(
			"Query the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}",
			channelId,
			chaincodeId, args.fcn, args.args
		)
		return request.let { jsonConverter.toCompletableObject(clazz).apply(it) }
	}

	suspend fun <T> list(query: HasList, clazz: Class<T>): List<T> {
		val args = query.listArgs()
		val request = coopRepository.query(
			cmd = QUERY,
			fcn = args.fcn,
			args = args.args,
			channelId = channelId,
			chaincodeId = chaincodeId,
		)
		logger.info(
			"List the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}",
			channelId,
			chaincodeId, args.fcn, args.args
		)
		return request.let { jsonConverter.toCompletableObjects(clazz).apply(it) }
	}

	@Throws(Exception::class)
	suspend operator fun invoke(cmdSigned: SsmCmdSigned): InvokeReturn? {
		val invokeArgs = cmdSigned.buildArgs()
		logger.info(
			"""
            Invoke the blockchain in channel[$channelId] 
            and ssm[$chaincodeId}] with command[${invokeArgs.fcn}] 
            with args:$invokeArgs
        """.trimIndent()
		)
		return coopRepository.invoke(
			cmd = INVOKE,
			fcn = invokeArgs.fcn,
			args = invokeArgs.args,
			channelId = channelId,
			chaincodeId = chaincodeId,
		).let { jsonConverter.toCompletableObject(InvokeReturn::class.java).apply(it) }
	}

	companion object {
		const val QUERY = "query"
		const val INVOKE = "invoke"
	}
}
