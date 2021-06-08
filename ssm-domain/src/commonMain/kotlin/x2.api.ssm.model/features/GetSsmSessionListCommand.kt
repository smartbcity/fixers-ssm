package x2.api.ssm.model.features

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SsmCommand
import ssm.dsl.query.SsmGetQuery
import x2.api.ssm.model.SsmBase
import x2.api.ssm.model.SsmSession
import x2.api.ssm.model.SsmSessionBase
import kotlin.js.JsExport
import kotlin.js.JsName

typealias GetSsmSessionListQueryFunction = F2Function<GetSsmSessionListCommand,  List<SsmSessionBase>>
typealias GetSsmSessionListQueryRemoteFunction = F2FunctionRemote<GetSsmSessionListCommand, Array<SsmSession>>


@JsExport
@JsName("GetSsmSessionListCommand")
interface GetSsmSessionListCommand: SsmCommand {
    override val baseUrl: String
    override val channelId: String?
    override val chaincodeId: String?
    override val bearerToken: String?
    val dbName: String
    val ssm: String?
}

@JsExport
@JsName("GetSsmSessionListCommandBase")
class GetSsmSessionListCommandBase(
    override val baseUrl: String,
    override val dbName: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?,
    override val ssm: String?
): GetSsmSessionListCommand
