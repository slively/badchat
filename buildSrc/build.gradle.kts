repositories {
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.5.30")
    implementation("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
}
