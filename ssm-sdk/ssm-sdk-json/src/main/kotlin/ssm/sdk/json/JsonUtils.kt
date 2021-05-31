package ssm.sdk.json

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.IOException

class JsonUtils {

	companion object {
		private val mapper: ObjectMapper = ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
			.registerModule(KotlinModule())

		@Throws(JsonProcessingException::class)
		fun toJson(obj: Any): String {
			return mapper.writeValueAsString(obj)
		}

		@Throws(IOException::class)
		fun <T> toObject(value: String, clazz: Class<T>): T {
			return mapper.readValue(value, clazz)
		}

		@Throws(IOException::class)
		fun <T> toObject(value: String, clazz: TypeReference<T>): T {
			return mapper.readValue(value, clazz)
		}
	}
}