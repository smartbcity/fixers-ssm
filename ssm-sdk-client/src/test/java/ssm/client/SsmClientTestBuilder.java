package ssm.client;

import java.io.IOException;

public class SsmClientTestBuilder {

    public static SsmClient build() throws IOException {
        String configFileName = getConfigFileName();
        return SsmClient.fromConfigFile(configFileName);
    }

    private static String getConfigFileName() {
        String profile = System.getenv("PROFILES_ACTIVE");
        System.out.println("////////////////////////////");
        System.out.println(profile);
        System.out.println("////////////////////////////");
        if("gitlab".equalsIgnoreCase(profile)) {
            return "ssm-client-gitlab.properties";
        }
        return "ssm-client.properties";
    }
}
