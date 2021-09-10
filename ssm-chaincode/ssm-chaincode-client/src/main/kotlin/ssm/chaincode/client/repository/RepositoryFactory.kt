package ssm.chaincode.client.repository

import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RepositoryFactory(baseUrl: String, bearerToken: String?) {

	private val retrofit: Retrofit

	companion object {
		const val TIMEOUT: Long = 20
		private var httpClient: OkHttpClient.Builder? = null
	}

	init {
		retrofit = buildRetrofit(baseUrl, bearerToken)
	}

	private fun buildRetrofit(baseUrl: String, bearerToken: String?): Retrofit {
		val client = buildHttpClient(bearerToken)
		return Retrofit.Builder()
			.baseUrl(baseUrl)
			.addConverterFactory(JacksonConverterFactory.create())
			.client(client)
			.build()
	}

	private fun buildHttpClient(bearerToken: String?): OkHttpClient {
		initializeHttpBuilder()
		httpClient!!.interceptors().clear()
		return httpClient!!.addInterceptor { chain: Interceptor.Chain ->
			val original = chain.request()
			var requestBuilder = original.newBuilder()
				.header("Content-Type", "application/json").method(original.method(), original.body())
			if (bearerToken != null && !bearerToken.isEmpty()) {
				requestBuilder = requestBuilder.header("Authorization", "Bearer $bearerToken")
			}
			chain.proceed(requestBuilder.build())
		}.build()
	}

	private fun initializeHttpBuilder() {
		if (httpClient != null) {
			return
		}
		httpClient = OkHttpClient.Builder()
			.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
			.readTimeout(TIMEOUT, TimeUnit.SECONDS)
			.writeTimeout(TIMEOUT, TimeUnit.SECONDS)
	}

	fun buildCoopRepository(): CoopRepository {
		return retrofit.create(CoopRepository::class.java)
	}
}
