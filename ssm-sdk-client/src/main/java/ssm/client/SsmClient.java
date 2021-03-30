package ssm.client;

import ssm.client.invoke.command.CreateCommandSigner;
import ssm.client.invoke.command.PerformCommandSigner;
import ssm.client.invoke.command.RegisterCommandSigner;
import ssm.client.invoke.command.StartCommandSigner;
import ssm.client.invoke.query.AdminQuery;
import ssm.client.invoke.query.AgentQuery;
import ssm.client.invoke.query.SessionQuery;
import ssm.client.invoke.query.SsmQuery;
import ssm.client.sign.model.Signer;
import ssm.dsl.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

    public CompletableFuture<InvokeReturn> registerUser(Signer signer, SsmAgent agent) throws Exception {
        RegisterCommandSigner cmd = new RegisterCommandSigner(signer, agent);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<InvokeReturn> create(Signer signer, Ssm ssm) throws Exception {
        CreateCommandSigner cmd = new CreateCommandSigner(signer, ssm);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<InvokeReturn> start(Signer signer, SsmSession session) throws Exception {
        StartCommandSigner cmd = new StartCommandSigner(signer, session);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<InvokeReturn> perform(Signer signer, String action, SsmContext context) throws Exception {
        PerformCommandSigner cmd = new PerformCommandSigner(signer, action, context);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<List<String>> listAdmins() {
        AdminQuery query = new AdminQuery();
        return ssmRequester.list(query, String.class);
    }

    public CompletableFuture<Optional<SsmAgent>> getAdmin(String username) {
        AdminQuery query = new AdminQuery();
        return ssmRequester.query(username, query, SsmAgent.class);
    }

    public CompletableFuture<List<String>> listAgent() {
        AgentQuery query = new AgentQuery();
        return ssmRequester.list(query, String.class);
    }

    public CompletableFuture<Optional<SsmAgent>> getAgent(String agentName)  {
        AgentQuery query = new AgentQuery();
        return ssmRequester.query(agentName, query, SsmAgent.class);
    }

    public CompletableFuture<List<String>> listSsm() {
        SsmQuery query = new SsmQuery();
        return ssmRequester.list(query, String.class);
    }

    public CompletableFuture<Optional<Ssm>> getSsm(String name) {
        SsmQuery query = new SsmQuery();
        return ssmRequester.query(name, query, Ssm.class);
    }

    public CompletableFuture<Optional<SsmSessionState>> getSession(String sessionId) {
        SessionQuery query = new SessionQuery();
        return ssmRequester.query(sessionId, query, SsmSessionState.class);
    }

    public CompletableFuture<List<String>> listSession() {
        SessionQuery query = new SessionQuery();
        return ssmRequester.list(query, String.class);
    }

}
