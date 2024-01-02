import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true

dependencies {
	//Spring Data 의존성
	runtimeOnly ("com.h2database:h2")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation(project(":core"))
}
