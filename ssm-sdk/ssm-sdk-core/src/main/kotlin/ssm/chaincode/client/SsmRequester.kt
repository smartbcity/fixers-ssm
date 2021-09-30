package ssm.chaincode.client

import org.slf4j.LoggerFactory
import ssm.chaincode.client.invoke.builder.HasGet
import ssm.chaincode.client.invoke.builder.HasList
import ssm.chaincode.client.model.SsmCmdSigned
import ssm.chaincode.client.model.buildArgs
import ssm.chaincode.client.repository.CommandArgs
import ssm.chaincode.client.repository.KtorRepository
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.sdk.json.JSONConverter

class SsmRequester(
	private val channelId: String?,
	private val chaincodeId: String?,
	private val jsonConverter: JSONConverter,
	private val coopRepository: KtorRepository,
) {
	private val logger = LoggerFactory.getLogger(SsmClient::class.java)
	suspend fun <T> log(value: String, query: HasGet, clazz: Class<T>): List<T> {
		val args = query.queryArgs(value)
		val request = coopRepository.invokeCommand(QUERY,
			channelId,
			chaincodeId, args.fcn, args.args
		)
		logger.info("Query the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}",
			channelId,
			chaincodeId, args.fcn, args.args
		)
		return request.let {
			jsonConverter.toCompletableObjects(clazz).apply(it)
		}
	}

	suspend fun <T> query(value: String, query: HasGet, clazz: Class<T>): T? {
		val args = query.queryArgs(value)
		val request = coopRepository.invokeCommand(QUERY,
			channelId,
			chaincodeId, args.fcn, args.args)
		logger.info("Query the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}",
			channelId,
			chaincodeId, args.fcn, args.args)
		return request.let {  jsonConverter.toCompletableObject(clazz).apply(it) }
	}

	suspend fun <T> list(query: HasList, clazz: Class<T>): List<T> {
		val args = query.listArgs()
		val request = coopRepository.invokeCommand(QUERY,
			channelId,
			chaincodeId, args.fcn, args.args)
		logger.info("List the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}",
			channelId,
			chaincodeId, args.fcn, args.args)
		return request.let{jsonConverter.toCompletableObjects(clazz).apply(it)}
	}

	@Throws(Exception::class)
	suspend operator fun invoke(cmdSigned: SsmCmdSigned): InvokeReturn? {
		val invokeArgs = cmdSigned.buildArgs()
		logger.info("""
            Invoke the blockchain in channel[$channelId] 
            and ssm[$chaincodeId}] with command[${invokeArgs.fcn}] 
            with args:$invokeArgs
        """.trimIndent())
		return coopRepository.invokeCommand(CommandArgs.from(INVOKE, invokeArgs, channelId, chaincodeId))
			.let { jsonConverter.toCompletableObject(InvokeReturn::class.java).apply(it) }
	}

	companion object {
		const val QUERY = "query"
		const val INVOKE = "invoke"
	}
}
