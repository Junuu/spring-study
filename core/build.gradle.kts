import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    //로그 의존성
	api("io.github.microutils:kotlin-logging-jvm:2.0.11")
}



