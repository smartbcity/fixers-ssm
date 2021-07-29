package ssm.sdk.json

import okhttp3.ResponseBody
import java.util.*
import java.util.function.Function

interface JSONConverter {
	fun <T> toCompletableObjects(clazz: Class<T>): Function<ResponseBody, List<T>>
	fun <T> toCompletableObject(clazz: Class<T>): Function<ResponseBody, T?>
	fun <T> toObject(clazz: Class<T>): Function<String, T?>
}