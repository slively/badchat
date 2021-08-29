package badchat

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    mavenCentral()
}

plugins {
    id("java")
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.spring")
    id("io.spring.dependency-management")
}


tasks.withType<Test> {
    useJUnitPlatform()
}


tasks.withType<JavaCompile> {
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
