@file:JvmName("AgentUtils")

package ssm.sdk.client.extention

import java.security.PublicKey
import ssm.chaincode.dsl.model.SsmAgent
import ssm.sdk.sign.crypto.KeyPairReader

@Throws(Exception::class)
fun loadFromFile(name: String): SsmAgent {
	val pub = KeyPairReader.loadPublicKey(name)
	return SsmAgent(name, pub.encoded)
}

@Throws(Exception::class)
fun loadFromFile(name: String, filename: String): SsmAgent {
	val pub = KeyPairReader.loadPublicKey(filename)
	return SsmAgent(name, pub.encoded)
}

@Throws(Exception::class)
fun SsmAgent.getPubAsKey(): PublicKey {
	return KeyPairReader.fromByteArray(pub)
}
