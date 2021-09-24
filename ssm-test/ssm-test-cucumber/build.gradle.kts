plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
	api("io.cucumber:cucumber-java8:${Versions.cucumber}")
	api("io.cucumber:cucumber-junit-platform-engine:${Versions.cucumber}")
	Dependencies.jvm.junit.forEach { api(it) }
}


configurations.create("cucumberRuntime") {
	extendsFrom(configurations["testImplementation"])
}

tasks.create("cucumber") {
	dependsOn("assemble", "compileTestKotlin")
	doLast {
		javaexec {
			mainClass.set("io.cucumber.core.cli.Main")
			classpath = configurations["cucumberRuntime"] + sourceSets["main"].output + sourceSets["test"].output
			args("--plugin", "pretty", "--glue", "kata.word.search.atdd", "src/test/features")
		}
	}
}
