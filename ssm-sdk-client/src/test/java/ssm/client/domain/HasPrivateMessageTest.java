package ssm.client.domain;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import ssm.client.AgentUtils;
import ssm.client.PrivateMessageUtils;
import ssm.client.SsmClientItTest;
import ssm.client.sign.crypto.KeyPairReader;
import ssm.client.sign.model.Signer;
import ssm.dsl.SsmAgent;
import ssm.dsl.SsmContext;

class HasPrivateMessageTest {

    @Test
    void shouldEncryptMessage() throws Exception {
        String sessionName = "deal20181201-" + UUID.randomUUID().toString();
        SsmContext context = new SsmContext(sessionName, "100 dollars 1978 Camaro", 0, new HashMap<>());

        SsmAgent agent = AgentUtils.loadFromFile(SsmClientItTest.USER1_NAME, SsmClientItTest.USER1_FILENAME);
        context = PrivateMessageUtils.addPrivateMessage(context, "Value to encrypt", agent);
        String val = context.getPrivate().get(agent.getName());
        Assertions.assertThat(val).isNotEmpty().isNotEqualTo("Value to encrypt");
    }

    @Test
    void shouldDecryptMessage() throws Exception {
        String sessionName = "deal20181201-" + UUID.randomUUID().toString();
        SsmContext context = new SsmContext(sessionName, "100 dollars 1978 Camaro", 0, new HashMap<>());
        SsmAgent agent = AgentUtils.loadFromFile(SsmClientItTest.USER1_NAME, SsmClientItTest.USER1_FILENAME);
        context = PrivateMessageUtils.addPrivateMessage(context, "Value to encrypt", agent);

        PrivateKey privKey = KeyPairReader.Companion.loadPrivateKey(SsmClientItTest.USER1_FILENAME);
        String val = PrivateMessageUtils.getPrivateMessage(context, SsmClientItTest.USER1_NAME, privKey);
        Assertions.assertThat(val).isNotEmpty().isEqualTo("Value to encrypt");
    }

    @Test
    void shouldDecryptMessageWithSigner() throws Exception {
        String sessionName = "deal20181201-" + UUID.randomUUID().toString();
        SsmContext context = new SsmContext(sessionName, "100 dollars 1978 Camaro", 0, new HashMap<>());
        SsmAgent agent = AgentUtils.loadFromFile(SsmClientItTest.USER1_NAME, SsmClientItTest.USER1_FILENAME);
        context = PrivateMessageUtils.addPrivateMessage(context, "Value to encrypt", agent);
        Signer signerUser1 = Signer.Companion.loadFromFile(SsmClientItTest.USER1_NAME, SsmClientItTest.USER1_FILENAME);
        String val = PrivateMessageUtils.getPrivateMessage(context, signerUser1);
        Assertions.assertThat(val).isNotEmpty().isEqualTo("Value to encrypt");
    }
}