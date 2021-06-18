package ssm.chaincode.client;

import java.io.IOException;

import com.google.common.base.Preconditions;
import ssm.chaincode.client.repository.CoopRepository;
import ssm.chaincode.client.repository.RepositoryFactory;
import ssm.sdk.json.JSONConverter;
import ssm.sdk.json.JSONConverterObjectMapper;

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
        JSONConverter converter = new JSONConverterObjectMapper();
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
