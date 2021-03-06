clean: clean-ssm-java

test: test-ssm-java

package: package-ssm-java

push: push-ssm-java

clean-ssm-java:
	./gradlew clean

test-ssm-java:
	./gradlew test

package-ssm-java:
	./gradlew build -x test

push-ssm-java:
	VERSION=${VERSION} ./gradlew publish