package ssm.sdk.json

import com.fasterxml.jackson.core.type.TypeReference
import java.io.IOException
import java.util.concurrent.CompletionException
import java.util.function.Function

class JSONConverterObjectMapper : JSONConverter {
	override fun <T> toCompletableObjects(clazz: Class<T>): Function<String, List<T>> {
		val type: TypeReference<List<T>> = object : TypeReference<List<T>>() {}
		return Function { response: String ->
			try {
				JsonUtils.toObject(response, type)
			} catch (e: IOException) {
				throw CompletionException("Error parsing response: $response", e)
			}
		}
	}

	override fun <T> toCompletableObject(clazz: Class<T>): Function<String, T?> {
		return Function { value: String ->
			toObject(clazz).apply(value)
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

}
