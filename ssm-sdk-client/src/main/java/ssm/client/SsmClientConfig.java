package ssm.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import ssm.client.sign.utils.FileUtils;

public class SsmClientConfig {

    private final static String SSM_REST_URL = "ssm.rest.url";

    public static SsmClientConfig fromConfigFile(String filename) throws IOException {
        URL file = FileUtils.Companion.getUrl(filename);
        Properties props = new Properties();
        props.load(new FileInputStream(file.getFile()));
        String baseUrl = props.getProperty(SSM_REST_URL);
        return new SsmClientConfig(baseUrl);
    }

    private final String baseUrl;
    private final String channelId;
    private final String chaincodeId;
    private final String bearerToken;

    public SsmClientConfig(String baseUrl) {
        this.baseUrl = baseUrl;
        this.channelId = null;
        this.chaincodeId = null;
        this.bearerToken = null;
    }

    public SsmClientConfig(String baseUrl, String channelId, String chaincodeId, String bearerToken) {
        this.baseUrl = baseUrl;
        this.channelId = channelId;
        this.chaincodeId = chaincodeId;
        this.bearerToken = bearerToken;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getChaincodeId() {
        return chaincodeId;
    }

    public String getBearerToken() {
        return bearerToken;
    }
}
