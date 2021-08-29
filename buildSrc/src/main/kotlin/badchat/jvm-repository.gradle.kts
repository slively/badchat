package badchat

plugins {
    id("badchat.jvm-base")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc:2.5.4")
}