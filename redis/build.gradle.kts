
import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true


dependencies {
	implementation(project(":core"))
	implementation ("org.redisson:redisson-spring-boot-starter:3.18.0")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	runtimeOnly ("com.h2database:h2")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
}
