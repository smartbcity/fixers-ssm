package ssm.f2.commons

import kotlinx.coroutines.future.await
import ssm.sdk.json.toInstanceOf
import ssm.sdk.json.toInstancesOf
import java.util.concurrent.CompletableFuture

suspend inline fun <reified T: Any> CompletableFuture<T>.awaitInstance(): T {
    return await().toInstanceOf(T::class.java)
}

suspend inline fun <reified T: Any> CompletableFuture<List<T>>.awaitInstances(): List<T> {
    return await().toInstancesOf(T::class.java)
}
