@file:JvmName("AgentUtils")
package ssm.chaincode.client

import org.bouncycastle.crypto.CryptoException
import ssm.chaincode.dsl.SsmAgent
import ssm.sdk.sign.crypto.KeyPairReader
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