@file:JvmName("AgentUtils")
package ssm.client

import org.bouncycastle.crypto.CryptoException
import ssm.client.sign.crypto.KeyPairReader
import ssm.dsl.SsmAgent
import java.security.PublicKey

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

@Throws(CryptoException::class)
fun SsmAgent.getPubAsKey(): PublicKey {
	return KeyPairReader.fromByteArray(pub)
}
