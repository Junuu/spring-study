import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true

plugins {
	kotlin("kapt") // Enable kapt for annotation processing
}

dependencies {
	implementation(project(":core"))
	implementation(project(":annotation-holder"))
	kapt (project(":annotation-processor-maker"))
}
