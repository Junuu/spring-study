import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true


dependencies {
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation(project(":core"))
}
