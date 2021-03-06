package ssm.client;

import okhttp3.ResponseBody;
import ssm.client.invoke.command.CommandSigner;
import ssm.client.invoke.command.InvokeArgs;
import ssm.client.json.JSONConverter;
import ssm.client.invoke.query.HasGet;
import ssm.client.invoke.query.HasList;
import ssm.client.repository.CommandArgs;
import ssm.client.repository.CoopRepository;
import ssm.dsl.InvokeReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class SsmRequester {

    public static final String QUERY = "query";
    public static final String INVOKE = "invoke";
    private final Logger logger = LoggerFactory.getLogger(SsmClient.class);

    private final CoopRepository coopRepository;
    private final JSONConverter jsonConverter;

    public SsmRequester(JSONConverter jsonConverter, CoopRepository coopRepository) {
        this.coopRepository = coopRepository;
        this.jsonConverter = jsonConverter;
    }

    public <T> CompletableFuture<Optional<T>> query(String value, HasGet query, Class<T> clazz) {
        InvokeArgs args = query.queryArgs(value);
        CompletableFuture<ResponseBody> request = coopRepository.command(QUERY, args.getFcn(), args.getArgs());

        logger.info("List the blockchain fcn[{}] with args:{}", args.getFcn(), args.getArgs());
        return request.thenApply(jsonConverter.toCompletableObject(clazz));
    }

    public <T> CompletableFuture<List<T>> list(HasList query, Class<T> clazz) {
        InvokeArgs args = query.listArgs();
        CompletableFuture<ResponseBody> request = coopRepository.command(QUERY, args.getFcn(), args.getArgs());

        logger.info("List the blockchain fcn[{}] with args:{}", args.getFcn(), args.getArgs());
        return request.thenApply(jsonConverter.toCompletableObjects(clazz));
    }

    public <T> CompletableFuture<InvokeReturn> invoke(CommandSigner<T> cmd) throws Exception {
        InvokeArgs invokeArgs = cmd.invoke();
        logger.info("Invoke the blockchain command[{}] with args:{}", cmd.getCommandName(), invokeArgs);
        return coopRepository.invoke(CommandArgs.from(INVOKE, invokeArgs))
                .thenApply(jsonConverter.toCompletableObject(InvokeReturn.class))
                .thenApply(Optional::get);
    }

}
