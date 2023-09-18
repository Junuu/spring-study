import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-batch")
	runtimeOnly("org.postgresql:postgresql:42.3.5")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.batch:spring-batch-test")
	implementation(project(":core"))
}
