plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "com.marossolutions.findyoursport"
version = "1.0.0"
application {
    mainClass.set("com.marossolutions.findyoursport.ApplicationKt")
    applicationDefaultJvmArgs =
        listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)

    // MySQL connector
    implementation(libs.mysql.connector.j)

    // Exposed
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)

    // Logging
    implementation(libs.ktor.server.call.logging.jvm)

    // Koin
    implementation(libs.koin.ktor)

    // Testing
    testImplementation(libs.kotlin.test.junit)
}