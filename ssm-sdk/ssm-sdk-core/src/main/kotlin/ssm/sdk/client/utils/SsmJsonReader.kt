package ssm.sdk.client.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmName
import ssm.sdk.json.JsonUtils
import ssm.sdk.sign.utils.FileUtils

class SsmJsonReader(
	private val objectMapper: ObjectMapper = JsonUtils.mapper
) {

	fun readSsm(ssmName: SsmName): Ssm {
		return readSsm("$ssmName.json", ssmName)
	}

	fun readSsm(fileName: String, ssmName: String): Ssm {
		val file = FileUtils.getReader(fileName)
		val ssm: Ssm = objectMapper.readValue(file)
		return Ssm(ssmName, ssm.transitions)
	}
}
