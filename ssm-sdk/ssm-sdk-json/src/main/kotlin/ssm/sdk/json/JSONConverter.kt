package ssm.sdk.json

import java.util.function.Function

interface JSONConverter {
	fun <T> toCompletableObjects(clazz: Class<T>): Function<String, List<T>>
	fun <T> toCompletableObject(clazz: Class<T>): Function<String, T?>
	fun <T> toObject(clazz: Class<T>): Function<String, T?>
}
