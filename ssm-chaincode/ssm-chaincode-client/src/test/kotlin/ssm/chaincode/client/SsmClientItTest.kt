package ssm.chaincode.client

import com.google.common.collect.ImmutableMap
import org.assertj.core.api.Assertions
import org.assertj.core.util.Lists
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import ssm.chaincode.dsl.*
import ssm.chaincode.dsl.blockchain.Block
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.sdk.sign.model.Signer
import java.util.*
import java.util.concurrent.CompletableFuture

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
		private lateinit var agentAdmin: SsmAgentBase
		private lateinit var agentUser1: SsmAgentBase
		private lateinit var agentUser2: SsmAgentBase
		private lateinit var client: SsmClient
		private lateinit var ssmName: String
		private lateinit var sessionName: String
		private lateinit var session: SsmSessionBase

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
			val roles: Map<String, String> = ImmutableMap.of(
				signerUser1.name, "Buyer", signerUser2.name, "Seller")
			sessionName = "deal20181201-" + uuid
			session = SsmSessionBase(ssmName,
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
	fun adminUser(): Unit {
		val agentRet = client.getAdmin(ADMIN_NAME)
		val agentFormClient = agentRet.get()
		Assertions.assertThat(agentFormClient.get()).isEqualTo(agentAdmin)
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
		Assertions.assertThat(agentFormClient.get()).isEqualTo(Companion.agentUser1)
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
	fun agentUser2(): Unit {
		val agentRet = client.getAgent(
			Companion.agentUser2.name)
		val agentFormClient = agentRet.get()
		Assertions.assertThat(agentFormClient.get()).isEqualTo(Companion.agentUser2)
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
		val sell = SsmTransitionBase(0, 1, "Seller", "Sell")
		val buy = SsmTransitionBase(1, 2, "Buyer", "Buy")
		val ssm = SsmBase(ssmName, Lists.newArrayList(sell, buy))
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
		Assertions.assertThat(ssm).isPresent
		Assertions.assertThat(ssm.get().name).isEqualTo(ssmName)
	}

	@Test
	@Order(80)
	fun start() {
		val roles: Map<String, String> = ImmutableMap.of(
			Companion.agentUser1.name, "Buyer", Companion.agentUser2.name, "Seller")
		val session = SsmSessionBase(ssmName,
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
		Assertions.assertThat(sesReq.get().current).isEqualTo(0)
		Assertions.assertThat(sesReq.get().iteration).isEqualTo(0)
		Assertions.assertThat(sesReq.get().origin).isNull()
		Assertions.assertThat(sesReq.get().ssm).isEqualTo(ssmName)
		Assertions.assertThat(sesReq.get().roles).isEqualTo(Companion.session.roles)
		Assertions.assertThat(sesReq.get().session).isEqualTo(Companion.session.session)
		Assertions.assertThat(sesReq.get().public).isEqualTo(Companion.session.public)
	}

	@Test
	@Order(100)
	fun performSell() {
		var context = SsmContextBase(sessionName, "100 dollars 1978 Camaro", 0, emptyMap())
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
		val sell = SsmTransitionBase(0, 1, "Seller", "Sell")
		val sesReq = client.getSession(
			sessionName)
		val state = sesReq.get()
		val stateExpected = SsmSessionStateBase(ssmName,
			sessionName, Companion.session.roles, "100 dollars 1978 Camaro", privateMessage, sell, 1, 1)
		Assertions.assertThat(state.get()).isEqualTo(stateExpected)
		val expectedMessage = stateExpected.getPrivateMessage(signerUser1)
	}

	@Order(110)
	@Test
	fun sessionAfterSellShouldReturnEncryptMessage(): Unit {
		val (from, to, role, action) = SsmTransitionBase(0, 1, "Seller", "Sell")
		val sesReq = client.getSession(
			sessionName)
		val state = sesReq.get()
		val expectedMessage = state.get().getPrivateMessage(signerUser1)
		Assertions.assertThat(expectedMessage).isEqualTo("Message to signer1")
	}

	@Test
	@Order(120)
	fun performBuy() {
		val context = SsmContextBase(sessionName, "Deal !", 1, emptyMap())
		val transactionEvent = client.perform(signerUser1, "Buy", context)
		val trans = transactionEvent.get()
		assertThatTransactionExists(trans)
	}

	fun assertThatTransactionExists(trans: InvokeReturn) {
		Assertions.assertThat(trans).isNotNull
		Assertions.assertThat(trans.status).isEqualTo("SUCCESS")
		val transaction: Transaction = client.getTransaction(trans.transactionId).get().orElse(null)
		Assertions.assertThat(transaction).isNotNull
		Assertions.assertThat(transaction.blockId).isNotNull
		val block: Block = client.getBlock(transaction.blockId).get().orElse(null)
		Assertions.assertThat(block).isNotNull
	}

	@Order(130)
	@Test
	fun sessionAfterBuy(): Unit {
		val buy = SsmTransitionBase(1, 2, "Buyer", "Buy")
		val sesReq = client.getSession(
			sessionName)
		val state = sesReq.get()
		val stateExcpected = SsmSessionStateBase(ssmName,
			sessionName, Companion.session.roles, "Deal !", emptyMap(), buy, 2, 2)
		Assertions.assertThat(state.get()).isEqualTo(stateExcpected)
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
	fun listSession() {
		val agentRet: CompletableFuture<List<String>> = client.listSession()
		val agentFormClient = agentRet.get()
		Assertions.assertThat(agentFormClient).contains(sessionName)
	}

}