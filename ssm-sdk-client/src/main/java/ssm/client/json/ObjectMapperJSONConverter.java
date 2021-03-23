package ssm.client.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Strings;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import ssm.client.Utils.JsonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionException;
import java.util.function.Function;

public class ObjectMapperJSONConverter implements JSONConverter {

    public <T> Function<ResponseBody, List<T>> toCompletableObjects(Class<T> clazz) {
        TypeReference<List<T>> type = new TypeReference<>() {
        };
        return value -> {
            String response = getString(value);
            try {
                return JsonUtils.toObject(response, type);
            } catch (IOException e) {
                throw new CompletionException("Error parsing response: " + response, e);
            }
        };
    }

    public <T> Function<ResponseBody, Optional<T>> toCompletableObject(Class<T> clazz) {
        return value -> {
            String response = getString(value);
            try {
                if (Strings.isNullOrEmpty(response)) {
                    return Optional.empty();
                }
                return Optional.of(JsonUtils.toObject(response, clazz));
            } catch (IOException e) {
                throw new CompletionException("Error parsing response: " + response, e);
            }
        };
    }

    @NotNull
    private String getString(ResponseBody value) throws CompletionException {
        try {
            return value.string();
        } catch (IOException e) {
            throw new CompletionException("Error reading response", e);
        }
    }
}
