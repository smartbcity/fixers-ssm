package ssm.client.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Strings;
import okhttp3.ResponseBody;
import ssm.client.Utils.JsonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionException;
import java.util.function.Function;

public class ObjectMapperJSONConverter implements JSONConverter {

    public <T> Function<ResponseBody, List<T>> toCompletableObjects(Class<T> clazz) {
        TypeReference<List<T>> type = new TypeReference<List<T>>(){};
        return value -> {
            try {
                return JsonUtils.toObject(value.string(), type);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        };
    }

    public <T> Function<ResponseBody, Optional<T>> toCompletableObject(Class<T> clazz) {
        return value -> {
            try {
                String response = value.string();
                if(Strings.isNullOrEmpty(response)){
                    return Optional.empty();
                }
                return  Optional.of(JsonUtils.toObject(response, clazz));
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        };
    }
}
