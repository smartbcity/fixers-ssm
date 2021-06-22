package ssm.chaincode.client

import ssm.sdk.sign.utils.FileUtils.Companion.getUrl
import java.io.FileInputStream
import java.io.IOException
import java.util.*

class SsmClientConfig(
    val baseUrl: String,
    val channelId: String? = null,
    val chaincodeId: String? = null,
    val bearerToken: String? = null
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