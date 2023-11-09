import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true


dependencies {
	implementation("org.springframework.kafka:spring-kafka")

	implementation(project(":core"))
	testImplementation("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:kafka")
	testImplementation("org.testcontainers:localstack")
	testImplementation("org.testcontainers:junit-jupiter")
}
