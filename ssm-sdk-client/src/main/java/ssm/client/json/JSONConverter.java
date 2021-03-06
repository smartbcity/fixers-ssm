package ssm.client.json;

import okhttp3.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface JSONConverter {

    <T> Function<ResponseBody, List<T>> toCompletableObjects(Class<T> clazz);
    <T> Function<ResponseBody, Optional<T>> toCompletableObject(Class<T> clazz);

}
