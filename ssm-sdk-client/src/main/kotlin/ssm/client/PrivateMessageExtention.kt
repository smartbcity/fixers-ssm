@file:JvmName("PrivateMessageUtils")
package ssm.client

import org.bouncycastle.crypto.CryptoException
import ssm.client.sign.crypto.RSACipher
import ssm.client.sign.model.Signer
import ssm.dsl.*
import java.security.PrivateKey
import java.security.PublicKey

@Throws(CryptoException::class)
fun SsmContext.addPrivateMessage(value: String, agent: SsmAgent): SsmContext {
	return addPrivateMessage(value, agent.name, agent.getPubAsKey())
}

@Throws(CryptoException::class)
fun SsmContext.addPrivateMessage(value: String, name: String, publicKey: PublicKey): SsmContext {
	val newMap = addPrivate(value, publicKey, name)
	return this.copy(private = newMap)
}


@Throws(CryptoException::class)
fun SsmSession.addPrivateMessage(value: String, agent: SsmAgent) {
	addPrivateMessage(value, agent.name, agent.getPubAsKey())
}

@Throws(CryptoException::class)
fun SsmSession.addPrivateMessage(value: String, name: String, publicKey: PublicKey): SsmSession {
	val newPrivate = addPrivate(value, publicKey, name)
	return SsmSession(
		ssm, session, roles, public, newPrivate
	)
}

@Throws(CryptoException::class)
fun SsmSessionState.addPrivateMessage(value: String, agent: SsmAgent) {
	addPrivateMessage(value, agent.name, agent.getPubAsKey())
}

@Throws(CryptoException::class)
fun SsmSessionState.addPrivateMessage(value: String, name: String, publicKey: PublicKey): SsmSessionState {
	val newPrivate = addPrivate(value, publicKey, name)
	return copy(private = newPrivate)
}

private fun WithPrivate.addPrivate(
		value: String,
		publicKey: PublicKey,
		name: String,
): Map<String, String> {
	val encrypted = RSACipher.encrypt(value.toByteArray(), publicKey)
	return (private ?: hashMapOf()) + mapOf(name to encrypted)
}

@Throws(CryptoException::class)
fun WithPrivate.getPrivateMessage(signer: Signer): String? {
	return getPrivateMessage(signer.name, signer.pair.private)
}


@Throws(CryptoException::class)
fun WithPrivate.getPrivateMessage(name: String, privateKey: PrivateKey): String? {
	val value = private?.get(name) ?: return null
	return RSACipher.decrypt(value, privateKey)
}
