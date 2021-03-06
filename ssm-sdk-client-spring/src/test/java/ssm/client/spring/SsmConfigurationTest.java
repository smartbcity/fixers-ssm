package ssm.client.spring;

import org.assertj.core.api.Assertions;
import ssm.client.SsmClient;
import ssm.client.SsmClientConfig;
import ssm.client.domain.Signer;
import org.civis.blockchain.ssm.client.spring.SsmBeanConfiguration;
import org.civis.blockchain.ssm.client.spring.SsmConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;


public class SsmConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(SsmBeanConfiguration.class));

    @Test
    public void shouldBuildSsmClientWhenSsmCoopUrlIsSet() {
        this.contextRunner
                .withPropertyValues("ssm.coop.url=http://localhost:9090")
                .run(context -> {
                    Assertions.assertThat(context.getBean(SsmConfiguration.class)).isNotNull();
                    assertThat(context.getBean(SsmBeanConfiguration.class)).isNotNull();
                    Assertions.assertThat(context.getBean(SsmClientConfig.class)).isNotNull();
                    assertThat(context.getBean(SsmClient.class)).isNotNull();
                });
    }

    @Test
    public void shouldBuildAdminWhenPropIsPresent() {
        this.contextRunner
                .withPropertyValues("ssm.coop.url=http://localhost:9090")
                .withPropertyValues("ssm.signer.admin.name=bob")
                .withPropertyValues("ssm.signer.admin.key=bob")
                .run(context -> {
                    Assertions.assertThat(context.getBean(Signer.class)).isNotNull();
                });
    }

}
