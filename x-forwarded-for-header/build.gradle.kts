import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true


dependencies {
	implementation(project(":core"))
	// https://mvnrepository.com/artifact/com.google.code.findbugs/jsr305
	implementation("com.google.code.findbugs:jsr305:3.0.2")
	// https://mvnrepository.com/artifact/com.github.spotbugs/spotbugs-annotations
	implementation("com.github.spotbugs:spotbugs-annotations:4.7.3")


}
