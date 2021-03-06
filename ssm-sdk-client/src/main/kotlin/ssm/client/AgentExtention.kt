@file:JvmName("AgentUtils")
package ssm.client

import org.bouncycastle.crypto.CryptoException
import ssm.client.crypto.KeyPairReader
import ssm.dsl.Agent
import java.security.PublicKey

@Throws(Exception::class)
fun loadFromFile(name: String): Agent {
	val pub = KeyPairReader.loadPublicKey(name)
	return Agent(name, pub.encoded)
}

@Throws(Exception::class)
fun loadFromFile(name: String, filename: String): Agent {
	val pub = KeyPairReader.loadPublicKey(filename)
	return Agent(name, pub.encoded)
}

@Throws(CryptoException::class)
fun Agent.getPubAsKey(): PublicKey {
	return KeyPairReader.fromByteArray(pub)
}
