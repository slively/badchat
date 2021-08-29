package badchat

repositories {
    mavenCentral()
}

plugins {
    id("badchat.jvm-base")
    id("badchat.jvm-repository")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.4")
}