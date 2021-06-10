package ssm.client;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import ssm.client.invoke.command.CreateCommandSigner;
import ssm.client.invoke.command.PerformCommandSigner;
import ssm.client.invoke.command.RegisterCommandSigner;
import ssm.client.invoke.command.StartCommandSigner;
import ssm.client.invoke.query.AdminQuery;
import ssm.client.invoke.query.AgentQuery;
import ssm.client.invoke.query.BlockQuery;
import ssm.client.invoke.query.LogQuery;
import ssm.client.invoke.query.SessionQuery;
import ssm.client.invoke.query.SsmQuery;
import ssm.client.invoke.query.TransactionQuery;
import ssm.client.sign.model.Signer;
import ssm.dsl.InvokeReturn;
import ssm.dsl.SsmAgentBase;
import ssm.dsl.SsmBase;
import ssm.dsl.SsmContextBase;
import ssm.dsl.SsmSessionBase;
import ssm.dsl.SsmSessionStateBase;
import ssm.dsl.SsmSessionStateLog;
import ssm.dsl.blockchain.BlockBase;
import ssm.dsl.blockchain.TransactionBase;

public class SsmClient {

    public static SsmClient fromConfigFile(String filename) throws IOException {
        return SsmClientBuilder.builder(filename).build();
    }

    public static SsmClient fromConfig(SsmClientConfig config) {
        return SsmClientBuilder.builder(config).build();
    }

    private final SsmRequester ssmRequester;

    public SsmClient(SsmRequester ssmRequester) {
        this.ssmRequester = ssmRequester;
    }

    public CompletableFuture<InvokeReturn> registerUser(Signer signer, SsmAgentBase agent) throws Exception {
        RegisterCommandSigner cmd = new RegisterCommandSigner(signer, agent);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<InvokeReturn> create(Signer signer, SsmBase ssm) throws Exception {
        CreateCommandSigner cmd = new CreateCommandSigner(signer, ssm);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<InvokeReturn> start(Signer signer, SsmSessionBase session) throws Exception {
        StartCommandSigner cmd = new StartCommandSigner(signer, session);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<InvokeReturn> perform(Signer signer, String action, SsmContextBase context) throws Exception {
        PerformCommandSigner cmd = new PerformCommandSigner(signer, action, context);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<List<String>> listAdmins() {
        AdminQuery query = new AdminQuery();
        return ssmRequester.list(query, String.class);
    }

    public CompletableFuture<Optional<SsmAgentBase>> getAdmin(String username) {
        AdminQuery query = new AdminQuery();
        return ssmRequester.query(username, query, SsmAgentBase.class);
    }

    public CompletableFuture<List<String>> listAgent() {
        AgentQuery query = new AgentQuery();
        return ssmRequester.list(query, String.class);
    }

    public CompletableFuture<Optional<SsmAgentBase>> getAgent(String agentName)  {
        AgentQuery query = new AgentQuery();
        return ssmRequester.query(agentName, query, SsmAgentBase.class);
    }

    public CompletableFuture<List<String>> listSsm() {
        SsmQuery query = new SsmQuery();
        return ssmRequester.list(query, String.class);
    }

    public CompletableFuture<Optional<SsmBase>> getSsm(String name) {
        SsmQuery query = new SsmQuery();
        return ssmRequester.query(name, query, SsmBase.class);
    }

    public CompletableFuture<Optional<SsmSessionStateBase>> getSession(String sessionId) {
        SessionQuery query = new SessionQuery();
        return ssmRequester.query(sessionId, query, SsmSessionStateBase.class);
    }


    public CompletableFuture<List<SsmSessionStateLog>> log(String sessionId) {
        LogQuery query = new LogQuery();
        return ssmRequester.log(sessionId, query, SsmSessionStateLog.class);
    }

    public CompletableFuture<List<String>> listSession() {
        SessionQuery query = new SessionQuery();
        return ssmRequester.list(query, String.class);
    }

    public CompletableFuture<Optional<TransactionBase>> getTransaction(String txId) {
        TransactionQuery query = new TransactionQuery();
        return ssmRequester.query(txId, query, TransactionBase.class);
    }

    public CompletableFuture<Optional<BlockBase>> getBlock(Long blockId) {
        BlockQuery query = new BlockQuery();
        return ssmRequester.query(blockId.toString(), query, BlockBase.class);
    }

}
