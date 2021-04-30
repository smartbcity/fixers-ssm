package ssm.client.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CoopRepository {

    @GET("/")
    CompletableFuture<ResponseBody> command(
            @Query("cmd") String cmd,
            @Query("channelid") String channelid,
            @Query("chaincodeid") String chaincodeid,
            @Query("fcn") String fcn,
            @Query("args") List<String> args
    );

    @POST("/")
    CompletableFuture<ResponseBody> invoke(
          @Body CommandArgs invokeArgs
    );

}
