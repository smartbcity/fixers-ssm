package ssm.chaincode.client.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.ResponseBody;
import retrofit2.http.*;

public interface CoopRepository {

    @GET("/")
    CompletableFuture<ResponseBody> command(
            @Query("cmd") String cmd,
            @Query("channelid") String channelid,
            @Query("chaincodeid") String chaincodeid,
            @Query("fcn") String fcn,
            @Query("args") List<String> args
    );


    @GET("/blocks/{blockId}")
    CompletableFuture<ResponseBody> getBlock(
            @Path("blockId") Long blockId,
            @Query("channelid") String channelid
    );

    @GET("/transactions/{txId}")
    CompletableFuture<ResponseBody> getTransaction(
            @Path("txId") String txId,
            @Query("channelid") String channelid
    );

    @POST("/")
    CompletableFuture<ResponseBody> invoke(
          @Body CommandArgs invokeArgs
    );

}
