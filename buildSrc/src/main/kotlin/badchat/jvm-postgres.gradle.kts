package badchat

plugins {
    id("badchat.jvm-repository")
}

dependencies {
    implementation("io.r2dbc:r2dbc-postgresql:0.8.8.RELEASE")
    implementation("org.postgresql:postgresql:42.2.23")
    implementation("io.zonky.test:embedded-postgres:1.3.1")
}