@file:JvmName("PrivateMessageUtils")
package ssm.client

import org.bouncycastle.crypto.CryptoException
import ssm.client.crypto.RSACipher
import ssm.client.domain.Signer
import ssm.dsl.*
import java.beans.Transient
import java.security.PrivateKey
import java.security.PublicKey

@Throws(CryptoException::class)
fun Context.addPrivateMessage(value: String, agent: Agent): Context {
	return addPrivateMessage(value, agent.name, agent.getPubAsKey())
}

@Throws(CryptoException::class)
fun Context.addPrivateMessage(value: String, name: String, publicKey: PublicKey): Context {
	val newMap = addPrivate(value, publicKey, name)
	return this.copy(private = newMap)
}


@Throws(CryptoException::class)
fun Session.addPrivateMessage(value: String, agent: Agent) {
	addPrivateMessage(value, agent.name, agent.getPubAsKey())
}

@Throws(CryptoException::class)
fun Session.addPrivateMessage(value: String, name: String, publicKey: PublicKey): Session {
	val newPrivate = addPrivate(value, publicKey, name)
	return Session(
		ssm, session, roles, public, newPrivate
	)
}

@Throws(CryptoException::class)
fun SessionState.addPrivateMessage(value: String, agent: Agent) {
	addPrivateMessage(value, agent.name, agent.getPubAsKey())
}

@Throws(CryptoException::class)
fun SessionState.addPrivateMessage(value: String, name: String, publicKey: PublicKey): SessionState {
	val newPrivate = addPrivate(value, publicKey, name)
	return copy(private = newPrivate)
}

private fun WithPrivate.addPrivate(
	value: String,
	publicKey: PublicKey,
	name: String,
): Map<String, String> {
	val encrypted = RSACipher.encrypt(value.toByteArray(), publicKey)
	val newMap = (private ?: hashMapOf()) + mapOf(name to encrypted)
	return newMap
}

@Throws(CryptoException::class)
fun WithPrivate.getPrivateMessage(signer: Signer): String? {
	return getPrivateMessage(signer.getName(), signer.getPair().getPrivate())
}


@Throws(CryptoException::class)
fun WithPrivate.getPrivateMessage(name: String, privateKey: PrivateKey): String? {
	val value = private?.get(name) ?: return null
	return RSACipher.decrypt(value, privateKey)
}
