package ssm.chaincode.client.repository

import okhttp3.ResponseBody
import retrofit2.http.*
import java.util.concurrent.CompletableFuture

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