package ssm.client;

import java.io.IOException;

import com.google.common.base.Preconditions;

import ssm.client.json.JSONConverter;
import ssm.client.json.ObjectMapperJSONConverter;
import ssm.client.repository.CoopRepository;
import ssm.client.repository.RepositoryFactory;

public class SsmClientBuilder {

    private CoopRepository coopRepository;
    private JSONConverter jsonConverter;

    private String channelId;
    private String ssmId;

    public static SsmClientBuilder builder() {
        return new SsmClientBuilder();
    }

    public static SsmClientBuilder builder(String filename) throws IOException {
        SsmClientConfig config = SsmClientConfig.fromConfigFile(filename);
        return builder(config);
    }


    public static SsmClientBuilder builder(SsmClientConfig config) {
        RepositoryFactory factory = new RepositoryFactory(config.getBaseUrl(), config.getBearerToken());
        CoopRepository coopRepository = factory.buildCoopRepository();
        JSONConverter converter = new ObjectMapperJSONConverter();
        return SsmClientBuilder.builder()
                .withCoopRepository(coopRepository)
                .withJSONConverter(converter)
                .withChannelId(config.getChannelId())
                .withSsmId(config.getChaincodeId());
    }



    public SsmClientBuilder() {
    }

    public SsmClientBuilder withCoopRepository(CoopRepository coopRepository) {
        this.coopRepository = coopRepository;
        return this;
    }

    public SsmClientBuilder withJSONConverter(JSONConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
        return this;
    }

    public SsmClientBuilder withChannelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public SsmClientBuilder withSsmId(String ssmId) {
        this.ssmId = ssmId;
        return this;
    }

    public SsmClient build() {
        Preconditions.checkNotNull(coopRepository);
        Preconditions.checkNotNull(jsonConverter);
        return new SsmClient(new SsmRequester(channelId, ssmId, jsonConverter, coopRepository));
    }
}
