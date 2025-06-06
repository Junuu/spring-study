import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true

dependencies {
	implementation(project(":core"))

	//Spring Data 의존성
	runtimeOnly ("com.h2database:h2")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.hibernate.orm:hibernate-core")
	implementation ("org.springframework.boot:spring-boot-starter-actuator")
}
