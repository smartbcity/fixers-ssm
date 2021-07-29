package ssm.sdk.json

import com.fasterxml.jackson.core.type.TypeReference
import okhttp3.ResponseBody
import java.io.IOException
import java.util.concurrent.CompletionException
import java.util.function.Function

class JSONConverterObjectMapper : JSONConverter {
	override fun <T> toCompletableObjects(clazz: Class<T>): Function<ResponseBody, List<T>> {
		val type: TypeReference<List<T>> = object : TypeReference<List<T>>() {}
		return Function { value: ResponseBody ->
			val response = getString(value)
			try {
				JsonUtils.toObject(response, type)
			} catch (e: IOException) {
				throw CompletionException("Error parsing response: $response", e)
			}
		}
	}

	override fun <T> toCompletableObject(clazz: Class<T>): Function<ResponseBody, T?> {
		return Function { value: ResponseBody ->
			getString(value)
		}.andThen { response ->
			toObject(clazz).apply(response)
		}
	}

	override fun <T> toObject(clazz: Class<T>): Function<String, T?> = Function<String, T?> { value: String ->
		try {
			if (value.isBlank()) {
				null
			} else {
				JsonUtils.toObject(value, clazz)
			}
		} catch (e: IOException) {
			throw CompletionException("Error parsing response: $value", e)
		}
	}

	@Throws(CompletionException::class)
	private fun getString(value: ResponseBody): String {
		return try {
			value.string()
		} catch (e: IOException) {
			throw CompletionException("Error reading response", e)
		}
	}
}