package ssm.sdk.core.ktor

import com.fasterxml.jackson.core.type.TypeReference
import org.slf4j.LoggerFactory
import ssm.sdk.core.invoke.builder.HasGet
import ssm.sdk.core.invoke.builder.HasList
import ssm.sdk.dsl.InvokeReturn
import ssm.sdk.dsl.SsmCmdSigned
import ssm.sdk.dsl.buildArgs
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
		logger.info(
			"Query[$QUERY] the blockchain in channel[${channelId}] and ssm[${chaincodeId}] with fcn[${args.fcn}] with args:${args.args}",
		)
		val request = coopRepository.query(
			cmd = QUERY,
			fcn = args.fcn,
			args = args.args,
			channelId = channelId,
			chaincodeId = chaincodeId,
		)
		return request.let {
			JsonUtils.toObject(it, clazz)
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
		return request.let { jsonConverter.toCompletableObject(clazz, it) }
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
		return request.let { jsonConverter.toCompletableObjects(clazz, it) }
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
		).let { jsonConverter.toCompletableObject(InvokeReturn::class.java, it) }
	}

	companion object {
		const val QUERY = "query"
		const val INVOKE = "invoke"
	}
}
