import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true


dependencies {
    implementation("ch.qos.logback:logback-core:1.3.5")
    implementation("ch.qos.logback:logback-classic:1.3.5")
    testImplementation("org.slf4j:slf4j-api:2.0.4")
    implementation(project(":core"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}

dependencyManagement {
    imports {
        // Spring Cloud BOM 추가 (버전은 현재 사용 중인 Spring Boot 버전에 맞게 설정)
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.3")
    }
}

