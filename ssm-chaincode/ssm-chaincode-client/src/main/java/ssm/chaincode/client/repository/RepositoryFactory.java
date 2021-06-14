package ssm.chaincode.client.repository;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RepositoryFactory {

    private static OkHttpClient.Builder httpClient;

    private final Retrofit retrofit;

    public RepositoryFactory(String baseUrl, String bearerToken) {
        this.retrofit = buildRetrofit(baseUrl, bearerToken);
    }

    private Retrofit buildRetrofit(String baseUrl, String bearerToken) {
        OkHttpClient client = buildHttpClient(bearerToken);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();
    }

    private OkHttpClient buildHttpClient(String bearerToken) {
        initializeHttpBuilder();
        httpClient.interceptors().clear();

        return httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json").method(original.method(), original.body());
            if (bearerToken != null && !bearerToken.isEmpty()) {
                requestBuilder = requestBuilder.header("Authorization", "Bearer " + bearerToken);
            }
            return chain.proceed(requestBuilder.build());
        }).build();
    }

    private void initializeHttpBuilder() {
        if (httpClient != null) {
            return;
        }

        httpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS);
    }

    public CoopRepository buildCoopRepository() {
        return retrofit.create(CoopRepository.class);
    }
}
