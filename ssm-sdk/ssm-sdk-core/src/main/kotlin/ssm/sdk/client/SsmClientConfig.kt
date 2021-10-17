package ssm.sdk.client

import java.io.FileInputStream
import java.io.IOException
import java.util.Properties
import ssm.sdk.sign.utils.FileUtils.getUrl

class SsmClientConfig(
	val baseUrl: String,
) {

	companion object {
		private const val SSM_REST_URL = "ssm.rest.url"

		@Throws(IOException::class)
		fun fromConfigFile(filename: String): SsmClientConfig {
			val file = getUrl(filename)
			val props = Properties()
			props.load(FileInputStream(file.file))
			val baseUrl = props.getProperty(SSM_REST_URL)
			return SsmClientConfig(baseUrl)
		}
	}
}
