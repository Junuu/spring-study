import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true

dependencies {
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.kafka:spring-kafka-test")
	implementation(project(":core"))
}
