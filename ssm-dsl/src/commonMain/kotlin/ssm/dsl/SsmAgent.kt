package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("Agent")
data class SsmAgent(
	val name: String,
	val pub: ByteArray,
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || this::class != other::class) return false

		other as SsmAgent

		if (name != other.name) return false
		if (!pub.contentEquals(other.pub)) return false

		return true
	}

	override fun hashCode(): Int {
		var result = name.hashCode()
		result = 31 * result + pub.contentHashCode()
		return result
	}
}