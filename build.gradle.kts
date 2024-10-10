plugins {
    kotlin("jvm") version "2.0.10"
    kotlin("plugin.spring") version "2.0.10"
    id("org.springframework.boot") version "3.1.4"  // 降级到 3.1.4
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.xiaoluo"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin 核心依赖
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Spring Boot 核心依赖
    implementation("org.springframework.boot:spring-boot-starter-web")

    // 日志依赖
    implementation("org.springframework.boot:spring-boot-starter-logging")

    // 数据库相关依赖 (如果项目需要使用 JPA 和数据库)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")

    // 测试依赖
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencyManagement {
    imports {
        // Spring Cloud BOM，管理 Spring Cloud 相关依赖版本
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.3")

        // Spring Boot BOM，确保所有子模块使用相同版本的 Spring Boot 依赖
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.1.4")  // 保持与 Spring Boot 3.1.4 一致
    }
}
