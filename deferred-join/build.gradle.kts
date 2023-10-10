import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true


dependencies {
	implementation(project(":core"))
	runtimeOnly("org.postgresql:postgresql:42.3.5")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
}
