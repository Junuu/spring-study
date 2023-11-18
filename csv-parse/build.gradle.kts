import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true


dependencies {
	implementation(project(":core"))

	// https://mvnrepository.com/artifact/com.opencsv/opencsv
	implementation("com.opencsv:opencsv:5.7.1")

}
