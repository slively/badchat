plugins {
    id("badchat.jvm-http-client")
}

dependencies {
    implementation(project(":kotlin:badchat:messages:public_api"))
}