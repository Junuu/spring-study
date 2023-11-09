import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true

plugins{
	id("io.gatling.gradle") version "3.9.5.1"
}

dependencies {
	implementation(project(":core"))
}
