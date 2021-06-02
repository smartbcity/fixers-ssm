package ssm.client;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.ResponseBody;
import ssm.client.invoke.command.CommandSigner;
import ssm.client.invoke.command.InvokeArgs;
import ssm.client.invoke.query.HasGet;
import ssm.client.invoke.query.HasList;
import ssm.client.repository.CommandArgs;
import ssm.client.repository.CoopRepository;
import ssm.dsl.InvokeReturn;
import ssm.sdk.json.JSONConverter;

public class SsmRequester {

    public static final String QUERY = "query";
    public static final String INVOKE = "invoke";
    private final Logger logger = LoggerFactory.getLogger(SsmClient.class);

    private final CoopRepository coopRepository;
    private final JSONConverter jsonConverter;

    private final String channelId;
    private final String chaincodeId;

    public SsmRequester(String channelId, String chaincodeId, JSONConverter jsonConverter, CoopRepository coopRepository) {
        this.coopRepository = coopRepository;
        this.jsonConverter = jsonConverter;
        this.channelId = channelId;
        this.chaincodeId = chaincodeId;
    }

    public <T> CompletableFuture<List<T>> log(String value, HasGet query, Class<T> clazz) {
        InvokeArgs args = query.queryArgs(value);
        CompletableFuture<ResponseBody> request = coopRepository.command(QUERY, channelId, chaincodeId, args.getFcn(), args.getArgs());

        logger.info("Query the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}", channelId, chaincodeId, args.getFcn(), args.getArgs());
        return request.thenApply(jsonConverter.toCompletableObjects(clazz));
    }

    public <T> CompletableFuture<Optional<T>> query(String value, HasGet query, Class<T> clazz) {
        InvokeArgs args = query.queryArgs(value);
        CompletableFuture<ResponseBody> request = coopRepository.command(QUERY, channelId, chaincodeId, args.getFcn(), args.getArgs());

        logger.info("Query the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}", channelId, chaincodeId, args.getFcn(), args.getArgs());
        return request.thenApply(jsonConverter.toCompletableObject(clazz));
    }

    public <T> CompletableFuture<List<T>> list(HasList query, Class<T> clazz) {
        InvokeArgs args = query.listArgs();
        CompletableFuture<ResponseBody> request = coopRepository.command(QUERY, channelId, chaincodeId, args.getFcn(), args.getArgs());

        logger.info("List the blockchain in channel[{}] and ssm[{}] with fcn[{}] with args:{}", channelId, chaincodeId, args.getFcn(), args.getArgs());
        return request.thenApply(jsonConverter.toCompletableObjects(clazz));
    }

    public <T> CompletableFuture<InvokeReturn> invoke(CommandSigner<T> cmd) throws Exception {
        InvokeArgs invokeArgs = cmd.invoke();
        logger.info("Invoke the blockchain in channel[{}] and ssm[{}] with command[{}] with args:{}", channelId, chaincodeId, cmd.getCommandName(), invokeArgs);
        return coopRepository.invoke(CommandArgs.from(INVOKE, invokeArgs, channelId, chaincodeId))
                .thenApply(jsonConverter.toCompletableObject(InvokeReturn.class))
                .thenApply(Optional::get);
    }
}
