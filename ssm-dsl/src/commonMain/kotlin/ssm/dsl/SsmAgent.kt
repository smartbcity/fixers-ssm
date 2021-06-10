package ssm.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmAgent")
interface SsmAgent {
	val name: String
	val pub: ByteArray
}

@Serializable
@JsExport
@JsName("SsmAgentBase")
data class SsmAgentBase(
	override val name: String,
	override val pub: ByteArray,
): SsmAgent {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || this::class != other::class) return false

		other as SsmAgentBase

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