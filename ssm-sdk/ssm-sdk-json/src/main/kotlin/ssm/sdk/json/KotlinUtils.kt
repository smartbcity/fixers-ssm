package ssm.sdk.json

fun <T : Any, O> T.toInstanceOf(outputClass: Class<O>): O {
	return JsonUtils.toJson(this)
		.let { JsonUtils.toObject(it, outputClass) }
}

fun <T : Any, O> List<T>.toInstancesOf(outputClass: Class<O>): List<O> {
	return map { it.toInstanceOf(outputClass) }
}
