package ssm.chaincode.f2.commons

import java.util.concurrent.CompletableFuture
import kotlinx.coroutines.future.await
import ssm.sdk.json.toInstanceOf
import ssm.sdk.json.toInstancesOf

suspend inline fun <reified T : Any> CompletableFuture<T>.awaitInstance(): T {
	return await().toInstanceOf(T::class.java)
}

suspend inline fun <reified T : Any> CompletableFuture<List<T>>.awaitInstances(): List<T> {
	return await().toInstancesOf(T::class.java)
}
