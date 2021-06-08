@file:JvmName("AgentUtils")
package ssm.client

import org.bouncycastle.crypto.CryptoException
import ssm.client.sign.crypto.KeyPairReader
import ssm.dsl.SsmAgentBase
import java.security.PublicKey

@Throws(Exception::class)
fun loadFromFile(name: String): SsmAgentBase {
	val pub = KeyPairReader.loadPublicKey(name)
	return SsmAgentBase(name, pub.encoded)
}

@Throws(Exception::class)
fun loadFromFile(name: String, filename: String): SsmAgentBase {
	val pub = KeyPairReader.loadPublicKey(filename)
	return SsmAgentBase(name, pub.encoded)
}

@Throws(CryptoException::class)
fun SsmAgentBase.getPubAsKey(): PublicKey {
	return KeyPairReader.fromByteArray(pub)
}
