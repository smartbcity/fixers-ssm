package ssm.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.google.common.collect.ImmutableMap;

import ssm.client.sign.model.Signer;
import ssm.dsl.InvokeReturn;
import ssm.dsl.Ssm;
import ssm.dsl.SsmAgent;
import ssm.dsl.SsmContext;
import ssm.dsl.SsmSession;
import ssm.dsl.SsmSessionState;
import ssm.dsl.SsmTransition;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SsmClientItTest {

    private static String uuid = UUID.randomUUID().toString();
    private static final String NETWORK = "bclan-it/";
    public static final String ADMIN_NAME = "ssm-admin";
    public static final String USER1_NAME = "bob" + "-" + uuid;
    public static final String USER2_NAME = "sam" + "-" + uuid;
    public static final String USER1_FILENAME = NETWORK + "bob";
    public static final String USER2_FILENAME = NETWORK + "sam";

//    private static final String NETWORK = "bc1/";
//    private static final String ADMIN_NAME = "adrien";
//    private static final String USER1_NAME = "chuck";
//    private static final String USER2_NAME = "sarah";
//    private static final String USER1_FILENAME = NETWORK+"chuck";
//    private static final String USER2_FILENAME = NETWORK+"sarah";

    private static Signer signerAdmin;
    private static Signer signerUser1;
    private static Signer signerUser2;

    private static SsmAgent agentAdmin;
    private static SsmAgent agentUser1;
    private static SsmAgent agentUser2;

    private static SsmClient client;
    private static String ssmName;
    private static String sessionName;
    private static SsmSession session;

    @BeforeAll
    public static void init() throws Exception {
        signerAdmin = Signer.Companion.loadFromFile(ADMIN_NAME, NETWORK + ADMIN_NAME);
        signerUser1 = Signer.Companion.loadFromFile(USER1_NAME, USER1_FILENAME);
        signerUser2 = Signer.Companion.loadFromFile(USER2_NAME, USER2_FILENAME);

        agentAdmin = AgentUtils.loadFromFile(ADMIN_NAME, NETWORK + ADMIN_NAME);
        agentUser1 = AgentUtils.loadFromFile(signerUser1.getName(), USER1_FILENAME);
        agentUser2 = AgentUtils.loadFromFile(signerUser2.getName(), USER2_FILENAME);

        client = SsmClientTestBuilder.build();

        ssmName = "CarDealership-" + uuid;
        Map<String, String> roles = ImmutableMap.of(signerUser1.getName(), "Buyer", signerUser2.getName(), "Seller");
        sessionName = "deal20181201-" + uuid;
        session = new SsmSession(ssmName, sessionName, roles,"Used car for 100 dollars.", new HashMap());
    }

    @AfterEach
    public void waitBetweenTest() throws InterruptedException {
        //Node rest api return http response before the transaction had been mined
        Thread.sleep(2000);
    }

    @Test
    @Order(5)
    public void getListAdmin() throws Exception {
        CompletableFuture<List<String>> agentRet = client.listAdmins();
        List<String> agentFormClient = agentRet.get();
        assertThat(agentFormClient).contains(ADMIN_NAME);
    }


    @Test
    @Order(10)
    public void getAdminUser() throws Exception {
        CompletableFuture<Optional<SsmAgent>> agentRet = client.getAdmin(ADMIN_NAME);
        Optional<SsmAgent> agentFormClient = agentRet.get();
        assertThat(agentFormClient.get()).isEqualTo(agentAdmin);
    }

    @Test
    @Order(20)
    public void registerUser1() throws Exception {
        CompletableFuture<InvokeReturn> transactionEvent = client.registerUser(signerAdmin, agentUser1);
        InvokeReturn trans = transactionEvent.get();

        assertThat(trans).isNotNull();
        assertThat(trans.getStatus()).isEqualTo("SUCCESS");
//        assertThat(trans.getTransactionId()).isNotNull();

    }

    @Test
    @Order(30)
    public void getAgentUser1() throws Exception {
        CompletableFuture<Optional<SsmAgent>> agentRet = client.getAgent(agentUser1.getName());
        Optional<SsmAgent> agentFormClient = agentRet.get();
        assertThat(agentFormClient.get()).isEqualTo(agentUser1);
    }

    @Test
    @Order(40)
    public void registerUser2() throws Exception {
        CompletableFuture<InvokeReturn> transactionEvent = client.registerUser(signerAdmin, agentUser2);
        InvokeReturn trans = transactionEvent.get();
        assertThat(trans).isNotNull();
        assertThat(trans.getStatus()).isEqualTo("SUCCESS");
//        assertThat(trans.getTransactionId()).isNotNull();
    }

    @Test
    @Order(50)
    public void getAgentUser2() throws Exception {
        CompletableFuture<Optional<SsmAgent>> agentRet = client.getAgent(agentUser2.getName());
        Optional<SsmAgent> agentFormClient = agentRet.get();
        assertThat(agentFormClient.get()).isEqualTo(agentUser2);
    }

    @Test
    @Order(55)
    public void listAgent() throws Exception {
        CompletableFuture<List<String>> agentRet = client.listAgent();
        List<String> agentFormClient = agentRet.get();
        assertThat(agentFormClient).contains(agentUser1.getName(), agentUser2.getName());
    }

    @Test
    @Order(60)
    public void createSsm() throws Exception {
        SsmTransition sell = new SsmTransition(0, 1, "Seller", "Sell");
        SsmTransition buy = new SsmTransition(1, 2, "Buyer", "Buy");
        Ssm ssm = new Ssm(ssmName, Lists.newArrayList(sell, buy));

        CompletableFuture<InvokeReturn> transactionEvent = client.create(signerAdmin, ssm);
        InvokeReturn trans = transactionEvent.get();

        assertThat(trans).isNotNull();
        assertThat(trans.getStatus()).isEqualTo("SUCCESS");
//        assertThat(trans.getTransactionId()).isNotNull();
    }

    @Test
    @Order(70)
    public void getSsm() throws Exception {
        CompletableFuture<Optional<Ssm>> ssmReq = client.getSsm(ssmName);
        Optional<Ssm> ssm = ssmReq.get();
        assertThat(ssm).isPresent();
        assertThat(ssm.get().getName()).isEqualTo(ssmName);
    }

    @Test
    @Order(80)
    public void start() throws Exception {
        Map<String, String> roles = ImmutableMap.of(agentUser1.getName(), "Buyer", agentUser2.getName(), "Seller");
        SsmSession session = new SsmSession(ssmName, sessionName, roles, "Used car for 100 dollars.", new HashMap());

        CompletableFuture<InvokeReturn> transactionEvent = client.start(signerAdmin, session);
        InvokeReturn trans = transactionEvent.get();

        assertThat(trans).isNotNull();
        assertThat(trans.getStatus()).isEqualTo("SUCCESS");
        assertThat(trans.getTransactionId()).isNotNull();
    }

    @Test
    @Order(90)
    public void getSession() throws Exception {
        CompletableFuture<Optional<SsmSessionState>> ses = client.getSession(sessionName);
        Optional<SsmSessionState> sesReq = ses.get();

        assertThat(sesReq.get().getCurrent()).isEqualTo(0);
        assertThat(sesReq.get().getIteration()).isEqualTo(0);
        assertThat(sesReq.get().getOrigin()).isNull();

        assertThat(sesReq.get().getSsm()).isEqualTo(ssmName);
        assertThat(sesReq.get().getRoles()).isEqualTo(session.getRoles());
        assertThat(sesReq.get().getSession()).isEqualTo(session.getSession());
        assertThat(sesReq.get().getPublic()).isEqualTo(session.getPublic());

    }

    private static Map<String, String> privateMessage;

    @Test
    @Order(100)
    public void performSell() throws Exception {
        SsmContext context = new SsmContext(sessionName, "100 dollars 1978 Camaro", 0, new HashMap());
        context = PrivateMessageUtils.addPrivateMessage(context, "Message to signer1", agentUser1);
        privateMessage = context.getPrivate();
        CompletableFuture<InvokeReturn> transactionEvent = client.perform(signerUser2, "Sell", context);
        InvokeReturn trans = transactionEvent.get();
        assertThat(trans).isNotNull();
        assertThat(trans.getStatus()).isEqualTo("SUCCESS");
//        assertThat(trans.getTransactionId()).isNotNull();
    }


    @Test
    @Order(110)
    public void getSessionAfterSell() throws Exception {
        SsmTransition sell = new SsmTransition(0, 1, "Seller", "Sell");
        CompletableFuture<Optional<SsmSessionState>> sesReq = client.getSession(sessionName);
        Optional<SsmSessionState> state = sesReq.get();
        SsmSessionState stateExpected = new SsmSessionState(ssmName, sessionName, session.getRoles(), "100 dollars 1978 Camaro", privateMessage, sell, 1, 1);
        assertThat(state.get()).isEqualTo(stateExpected);

        String expectedMessage = PrivateMessageUtils.getPrivateMessage(stateExpected, signerUser1);
    }

    @Test
    @Order(110)
    public void getSessionAfterSellShouldReturnEncryptMessage() throws Exception {
        SsmTransition sell = new SsmTransition(0, 1, "Seller", "Sell");
        CompletableFuture<Optional<SsmSessionState>> sesReq = client.getSession(sessionName);
        Optional<SsmSessionState> state = sesReq.get();
        String expectedMessage = PrivateMessageUtils.getPrivateMessage(state.get(), signerUser1);
        assertThat(expectedMessage).isEqualTo("Message to signer1");

    }

    @Test
    @Order(120)
    public void performBuy() throws Exception {
        SsmContext context = new SsmContext(sessionName, "Deal !", 1, new HashMap());
        CompletableFuture<InvokeReturn> transactionEvent = client.perform(signerUser1, "Buy", context);
        InvokeReturn trans = transactionEvent.get();
        assertThat(trans).isNotNull();
        assertThat(trans.getStatus()).isEqualTo("SUCCESS");
    }

    @Test
    @Order(130)
    public void getSessionAfterBuy() throws Exception {
        SsmTransition buy = new SsmTransition(1, 2, "Buyer", "Buy");
        CompletableFuture<Optional<SsmSessionState>> sesReq = client.getSession(sessionName);
        Optional<SsmSessionState> state = sesReq.get();
        SsmSessionState stateExcpected = new SsmSessionState(ssmName, sessionName, session.getRoles(),"Deal !", new HashMap(), buy, 2, 2);
        assertThat(state.get()).isEqualTo(stateExcpected);
    }

    @Test
    @Order(140)
    public void listSsm() throws Exception {
        CompletableFuture<List<String>> agentRet = client.listSsm();
        List<String> agentFormClient = agentRet.get();
        assertThat(agentFormClient).contains(ssmName);
    }

    @Test
    @Order(150)
    public void listSession() throws Exception {
        CompletableFuture<List<String>> agentRet = client.listSession();
        List<String> agentFormClient = agentRet.get();
        assertThat(agentFormClient).contains(sessionName);
    }


}
