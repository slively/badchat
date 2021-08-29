plugins {
    id("badchat.jvm-app")
    id("badchat.jvm-repository")
    id("badchat.jvm-postgres")
    id("org.springframework.boot").version("2.5.2")
    id("org.flywaydb.flyway").version("7.14.0")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-jdbc") // for flyway
    implementation("org.flywaydb:flyway-core:7.14.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")

    implementation(project(":kotlin:badchat:messages:ctrl"))
    implementation(project(":kotlin:badchat:messages:public_api"))
    implementation(project(":kotlin:common:exceptions"))
    implementation(project(":kotlin:common:ctrl_utils"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    args = listOf("--spring.profiles.active=dev")
}
