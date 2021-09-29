package ssm.chaincode.client.repository

import java.util.concurrent.CompletableFuture
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CoopRepository {
	@GET("/")
	fun command(
		@Query("cmd") cmd: String?,
		@Query("channelid") channelid: String?,
		@Query("chaincodeid") chaincodeid: String?,
		@Query("fcn") fcn: String?,
		@Query("args") args: List<String?>?,
	): CompletableFuture<ResponseBody>

	@GET("/blocks/{blockId}")
	fun getBlock(
		@Path("blockId") blockId: Long?,
		@Query("channelid") channelid: String?,
	): CompletableFuture<ResponseBody>

	@GET("/transactions/{txId}")
	fun getTransaction(
		@Path("txId") txId: String?,
		@Query("channelid") channelid: String?,
	): CompletableFuture<ResponseBody>

	@POST("/")
	operator fun invoke(
		@Body invokeArgs: CommandArgs?,
	): CompletableFuture<ResponseBody>
}
