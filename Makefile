STORYBOOK_DOCKERFILE	:= infra/docker/storybook/Dockerfile
STORYBOOK_NAME	   	 	:= smartbcity/ssm-storybook
STORYBOOK_IMG	    	:= ${STORYBOOK_NAME}:${VERSION}
STORYBOOK_LATEST		:= ${STORYBOOK_NAME}:latest

clean: clean-ssm-java

test: test-ssm-java

package: package-ssm-java package-storybook

push: push-ssm-java push-storybook

push-latest: push-latest-storybook

clean-ssm-java:
	./gradlew clean

test-ssm-java:
	./gradlew test

package-ssm-java:
	./gradlew build -x test

push-ssm-java:
	VERSION=${VERSION} ./gradlew publish

package-storybook:
	@docker build -f ${STORYBOOK_DOCKERFILE} -t ${STORYBOOK_IMG} .

push-storybook:
	@docker push ${STORYBOOK_IMG}

push-latest-storybook:
	@docker tag ${STORYBOOK_IMG} ${STORYBOOK_LATEST}
	@docker push ${STORYBOOK_LATEST}