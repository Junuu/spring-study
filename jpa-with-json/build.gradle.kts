import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true

dependencies {
	implementation(project(":core"))

	//Spring Data 의존성
	runtimeOnly ("mysql:mysql-connector-java:8.0.28")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-starter-actuator")

	//json column 활용하기 위한 hibernate-type 의존성 - hibernate 6.2 기준
	implementation ("io.hypersistence:hypersistence-utils-hibernate-62:3.7.0")
	implementation ("com.fasterxml.jackson.module:jackson-module-jakarta-xmlbind-annotations")

}
