plugins {
	kotlin("jvm") version "1.9.0"
	id("no.ghpkg") version "0.3.1"
	`maven-publish`
}

group = "no.telenor.kt"
version = versioning.environment()

repositories {
	mavenCentral()
}

dependencies {
	testImplementation(kotlin("test"))
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(17)
}

publishing {
	repositories.github.actions()
	publications.register<MavenPublication>("gpr") {
		from(components["kotlin"])
	}
}
