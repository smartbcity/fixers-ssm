package ssm.chaincode.client

import java.util.UUID
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions
import org.assertj.core.util.Lists
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import ssm.chaincode.client.extention.loadFromFile
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmContext
import ssm.chaincode.dsl.model.SsmSession
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmTransition
import ssm.chaincode.dsl.blockchain.Block
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.sdk.sign.model.Signer

@TestMethodOrder(OrderAnnotation::class)
class SsmClientItTest {

	companion object {
		private val uuid = UUID.randomUUID().toString()
		private const val NETWORK = "bclan-it/"
		const val ADMIN_NAME = "ssm-admin"
		val USER1_NAME = "bob" + "-" + uuid
		val USER2_NAME = "sam" + "-" + uuid
		const val USER1_FILENAME = NETWORK + "bob"
		const val USER2_FILENAME = NETWORK + "sam"

		//    private static final String NETWORK = "bc1/";
		//    private static final String ADMIN_NAME = "adrien";
		//    private static final String USER1_NAME = "chuck";
		//    private static final String USER2_NAME = "sarah";
		//    private static final String USER1_FILENAME = NETWORK+"chuck";
		//    private static final String USER2_FILENAME = NETWORK+"sarah";
		private lateinit var signerAdmin: Signer
		private lateinit var signerUser1: Signer
		private lateinit var signerUser2: Signer
		private lateinit var agentAdmin: SsmAgent
		private lateinit var agentUser1: SsmAgent
		private lateinit var agentUser2: SsmAgent
		private lateinit var client: SsmClient
		private lateinit var ssmName: String
		private lateinit var sessionName: String
		private lateinit var session: SsmSession

		@BeforeAll
		@JvmStatic
		@Throws(Exception::class)
		fun init() {
			signerAdmin = Signer.loadFromFile(ADMIN_NAME, NETWORK + ADMIN_NAME)
			signerUser1 = Signer.loadFromFile(USER1_NAME, USER1_FILENAME)
			signerUser2 = Signer.loadFromFile(USER2_NAME, USER2_FILENAME)
			agentAdmin = loadFromFile(ADMIN_NAME, NETWORK + ADMIN_NAME)
			agentUser1 = loadFromFile(signerUser1.name, USER1_FILENAME)
			agentUser2 = loadFromFile(signerUser2.name, USER2_FILENAME)
			client = SsmClientTestBuilder.build()
			ssmName = "CarDealership-" + uuid
			val roles = mapOf(
				signerUser1.name to "Buyer", signerUser2.name to "Seller")
			sessionName = "deal20181201-" + uuid
			session = SsmSession(ssmName,
				sessionName, roles, "Used car for 100 dollars.", emptyMap())
		}

		private var privateMessage: Map<String, String>? = null
	}

	@AfterEach
	fun waitBetweenTest() {
		//Node rest api return http response before the transaction had been mined
		Thread.sleep(2000)
	}

	@Order(5)
	@Test
	fun listAdmin(): Unit {
		val agentRet = client.listAdmins()
		val agentFormClient = agentRet.get()
		Assertions.assertThat(agentFormClient).contains(ADMIN_NAME)
	}

	@Order(10)
	@Test
	fun adminUser() {
		val agentRet = client.getAdmin(ADMIN_NAME)
		val agentFormClient = agentRet.get()
		Assertions.assertThat(agentFormClient).isEqualTo(agentAdmin)
	}

	@Test
	@Order(20)
	fun registerUser1() {
		val transactionEvent = client.registerUser(signerAdmin, Companion.agentUser1)
		val trans = transactionEvent.get()
		assertThatTransactionExists(trans)
	}

	@Order(30)
	@Test
	fun agentUser1(): Unit {
		val agentRet = client.getAgent(
			Companion.agentUser1.name)
		val agentFormClient = agentRet.get()
		Assertions.assertThat(agentFormClient).isEqualTo(Companion.agentUser1)
	}

	@Test
	@Order(40)
	fun registerUser2() {
		val transactionEvent = client.registerUser(signerAdmin, Companion.agentUser2)
		val trans = transactionEvent.get()
		assertThatTransactionExists(trans)
	}

	@Order(50)
	@Test
	fun agentUser2() {
		val agentRet = client.getAgent(Companion.agentUser2.name)
		val agentFormClient = agentRet.get()
		Assertions.assertThat(agentFormClient).isEqualTo(Companion.agentUser2)
	}

	@Test
	@Order(55)
	fun listAgent() {
		val agentRet = client.listAgent()
		val agentFormClient = agentRet.get()
		Assertions.assertThat(agentFormClient).contains(Companion.agentUser1.name, Companion.agentUser2.name)
	}

	@Test
	@Order(60)
	fun createSsm() {
		val sell = SsmTransition(0, 1, "Seller", "Sell")
		val buy = SsmTransition(1, 2, "Buyer", "Buy")
		val ssm = Ssm(ssmName, Lists.newArrayList(sell, buy))
		val transactionEvent = client.create(signerAdmin, ssm)
		val trans = transactionEvent.get()
		assertThatTransactionExists(trans)
	}

	@Order(70)
	@Test
	fun ssm() {
		val ssmReq = client.getSsm(
			ssmName)
		val ssm = ssmReq.get()
		Assertions.assertThat(ssm).isNotNull
		Assertions.assertThat(ssm?.name).isEqualTo(ssmName)
	}

	@Test
	@Order(80)
	fun start() {
		val roles: Map<String, String> = mapOf(
			agentUser1.name to "Buyer", agentUser2.name to "Seller")
		val session = SsmSession(ssmName,
			sessionName, roles, "Used car for 100 dollars.", emptyMap())
		val transactionEvent = client.start(signerAdmin, session)
		val trans = transactionEvent.get()
		assertThatTransactionExists(trans)
	}

	@Order(90)
	@Test
	fun session() {
		val ses = client.getSession(
			sessionName)
		val sesReq = ses.get()
		Assertions.assertThat(sesReq?.current).isEqualTo(0)
		Assertions.assertThat(sesReq?.iteration).isEqualTo(0)
		Assertions.assertThat(sesReq?.origin).isNull()
		Assertions.assertThat(sesReq?.ssm).isEqualTo(ssmName)
		Assertions.assertThat(sesReq?.roles).isEqualTo(Companion.session.roles)
		Assertions.assertThat(sesReq?.session).isEqualTo(Companion.session.session)
		Assertions.assertThat(sesReq?.public).isEqualTo(Companion.session.public)
	}

	@Test
	@Order(100)
	fun performSell() {
		var context = SsmContext(sessionName, "100 dollars 1978 Camaro", 0, emptyMap())
		context = context.addPrivateMessage("Message to signer1",
			Companion.agentUser1)
		privateMessage = context.private
		val transactionEvent = client.perform(signerUser2, "Sell", context)
		val trans = transactionEvent.get()
		assertThatTransactionExists(trans)
	}

	@Order(110)
	@Test
	fun sessionAfterSell(): Unit {
		val sell = SsmTransition(0, 1, "Seller", "Sell")
		val sesReq = client.getSession(
			sessionName)
		val state = sesReq.get()
		val stateExpected = SsmSessionState(ssmName,
			sessionName, session.roles, "100 dollars 1978 Camaro", privateMessage, sell, 1, 1)
		Assertions.assertThat(state).isEqualTo(stateExpected)
		val expectedMessage = stateExpected.getPrivateMessage(signerUser1)
	}

	@Order(110)
	@Test
	fun sessionAfterSellShouldReturnEncryptMessage(): Unit {
		val (from, to, role, action) = SsmTransition(0, 1, "Seller", "Sell")
		val sesReq = client.getSession(
			sessionName)
		val state = sesReq.get()
		val expectedMessage = state?.getPrivateMessage(signerUser1)
		Assertions.assertThat(expectedMessage).isEqualTo("Message to signer1")
	}

	@Test
	@Order(120)
	fun performBuy() {
		val context = SsmContext(sessionName, "Deal !", 1, emptyMap())
		val transactionEvent = client.perform(signerUser1, "Buy", context)
		val trans = transactionEvent.get()
		assertThatTransactionExists(trans)
	}

	fun assertThatTransactionExists(trans: InvokeReturn) {
		Assertions.assertThat(trans).isNotNull
		Assertions.assertThat(trans.status).isEqualTo("SUCCESS")
		val transaction: Transaction? = client.getTransaction(trans.transactionId).get()
		Assertions.assertThat(transaction).isNotNull
		Assertions.assertThat(transaction?.blockId).isNotNull
		val block: Block? = client.getBlock(transaction!!.blockId).get()
		Assertions.assertThat(block).isNotNull
	}

	@Order(130)
	@Test
	fun sessionAfterBuy(): Unit {
		val buy = SsmTransition(1, 2, "Buyer", "Buy")
		val sesReq = client.getSession(
			sessionName)
		val state = sesReq.get()
		val stateExcpected = SsmSessionState(ssmName,
			sessionName, session.roles, "Deal !", emptyMap(), buy, 2, 2)
		Assertions.assertThat(state).isEqualTo(stateExcpected)
	}

	@Test
	@Order(135)
	@Throws(Exception::class)
	fun logSession() {
		val sesReq = client.log(
			sessionName)
		val stateLog = sesReq.get()
		Assertions.assertThat(stateLog.size).isEqualTo(3)
	}

	@Test
	@Order(140)
	fun listSsm() {
		val agentRet: CompletableFuture<List<String>> = client.listSsm()
		val agentFormClient = agentRet.get()
		Assertions.assertThat(agentFormClient).contains(ssmName)
	}

	@Test
	@Order(150)
	private fun listSession() {
		val agentRet: CompletableFuture<List<String>> = client.listSession()
		val agentFormClient = agentRet.get()
		Assertions.assertThat(agentFormClient).contains(sessionName)
	}
}
