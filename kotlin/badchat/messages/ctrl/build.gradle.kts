plugins {
    id("badchat.jvm-ctrl")
}

dependencies {
    implementation(project(":kotlin:common:exceptions"))
    implementation(project(":kotlin:badchat:messages:public_api"))
    implementation(project(":kotlin:badchat:messages:repo"))
}