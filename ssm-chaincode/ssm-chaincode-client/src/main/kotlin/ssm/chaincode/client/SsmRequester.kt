package ssm.chaincode.client

import org.slf4j.LoggerFactory
import ssm.chaincode.client.invoke.command.CommandSigner
import ssm.chaincode.client.invoke.query.HasGet
import ssm.chaincode.client.invoke.query.HasList
import ssm.chaincode.client.repository.CommandArgs
import ssm.chaincode.client.repository.CoopRepository
import ssm.chaincode.dsl.InvokeReturn
import ssm.sdk.json.JSONConverter
import java.util.*
import java.util.concurrent.CompletableFuture

class SsmRequester(
    private val channelId: String?,
    private val chaincodeId: String?,
    private val jsonConverter: JSONConverter,
    private val coopRepository: CoopRepository,
) {
    private val logger = LoggerFactory.getLogger(SsmClient::class.java)
    fun <T> log(value: String, query: HasGet, clazz: Class<T>): CompletableFuture<List<T>> {
        val args = query.queryArgs(value)
        val request = coopRepository.command(QUERY,
            channelId,
            chaincodeId, args.fcn, args.args)
        logger.info("Query the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}",
            channelId,
            chaincodeId, args.fcn, args.args)
        return request.thenApply(jsonConverter.toCompletableObjects(clazz))
    }

    fun <T> query(value: String, query: HasGet, clazz: Class<T>): CompletableFuture<Optional<T>> {
        val args = query.queryArgs(value)
        val request = coopRepository.command(QUERY,
            channelId,
            chaincodeId, args.fcn, args.args)
        logger.info("Query the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}",
            channelId,
            chaincodeId, args.fcn, args.args)
        return request.thenApply(jsonConverter.toCompletableObject(clazz))
    }

    fun <T> list(query: HasList, clazz: Class<T>): CompletableFuture<List<T>> {
        val args = query.listArgs()
        val request = coopRepository.command(QUERY,
            channelId,
            chaincodeId, args.fcn, args.args)
        logger.info("List the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}",
            channelId,
            chaincodeId, args.fcn, args.args)
        return request.thenApply(jsonConverter.toCompletableObjects(clazz))
    }

    @Throws(Exception::class)
    operator fun <T> invoke(cmd: CommandSigner<T>): CompletableFuture<InvokeReturn> {
        val invokeArgs = cmd.invoke()
        logger.info("Invoke the blockchain in channel[${channelId}}] and ssm[$chaincodeId}] with command[${cmd.commandName}] with args:${invokeArgs}")
        return coopRepository.invoke(CommandArgs.from(INVOKE, invokeArgs, channelId, chaincodeId))
            .thenApply(jsonConverter.toCompletableObject(InvokeReturn::class.java))
            .thenApply(Optional<InvokeReturn>::get)
    }

    companion object {
        const val QUERY = "query"
        const val INVOKE = "invoke"
    }
}