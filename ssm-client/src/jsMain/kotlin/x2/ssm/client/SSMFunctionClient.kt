package x2.ssm.client

import GetSsmListCommand
import GetSsmSessionListCommand
import GetSsmSessionQueryRemoteFunction
import TxSsm
import TxSsmSession
import f2.client.F2Client
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.HTTP
import f2.client.ktor.get
import f2.client.promise
import f2.dsl.function.F2FunctionRemote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import ssm.chaincode.dsl.SsmSessionStateLog
import ssm.chaincode.dsl.query.SsmGetQueryRemoteFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import kotlin.js.Promise


@JsName("ssmClient")
@JsExport
fun ssmClient(host: String, port: Int, path: String? = null): Promise<SSMFunctionClient> =
	GlobalScope.promise {
		val s2Client = F2ClientBuilder.get(HTTP, host, port, path)
		SSMFunctionClient(s2Client)
	}

@JsExport
@JsName("SSMFunctionClient")
actual open class SSMFunctionClient actual constructor(private val client: F2Client) {

	@JsName("getAllSsm")
	fun getAllSsm(msg: GetSsmListCommand): Promise<Array<TxSsm>> = invokePromise<GetSsmListCommand, String> { msg ->
		client.promise("getAllSsm", msg)
	}.invoke(msg).then {
		JSON.parse(it)
	}

	@JsName("getSsm")
	fun getSsm(): SsmGetQueryRemoteFunction = invokePromise { msg ->
		client.promise("getAllSsm", msg)
	}

	@JsName("getAllSessions")
	fun getAllSessions(msg: GetSsmSessionListCommand): Promise<Array<TxSsmSession>> = invokePromise<GetSsmSessionListCommand, Array<TxSsmSession>> { msg ->
		client.promise("getAllSessions", msg)
	}.invoke(msg)


	@JsName("getSession")
	fun getSession(): GetSsmSessionQueryRemoteFunction = invokePromise { msg ->
		client.promise("getSession", msg)
	}

	@JsName("getSessionLogs")
	fun getSessionLogs(): F2FunctionRemote<SsmGetSessionLogsQuery, Array<SsmSessionStateLog>> =
		invokePromise { msg ->
			client.promise("getSessionLogs", msg)
		}

}

@JsExport
class F2Promise<T>(
	private val promise: Promise<T>
	) {

	public open fun <S> then(onFulfilled: ((T) -> S)?, onRejected: ((Throwable) -> S)?): Promise<S> =
		promise.then(onFulfilled, onRejected)

	public open fun <S> catch(onRejected: (Throwable) -> S): Promise<S> =
		promise.catch(onRejected)

}

fun <E, R> invokePromise(invoke: (e: E) -> Promise<R>) = object : F2FunctionRemote<E, R> {
	override fun invoke(cmd: E): Promise<R> = invoke(cmd)
}
