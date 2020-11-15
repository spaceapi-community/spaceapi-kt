import org.gradle.api.tasks.testing.logging.TestExceptionFormat

version = "0.1.0-SNAPSHOT"

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm") version "1.4.10"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    // Use Kotlin serialization library
    kotlin("plugin.serialization") version "1.4.10"
}

repositories {
    // Use jcenter for resolving dependencies.
    jcenter()
}

java {
    withSourcesJar()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // Use serialization library
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

tasks.jar {
    manifest {
        attributes(mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
        ))
    }
}

tasks.test {
    useJUnit()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        showCauses = true
        showExceptions = true
        showStackTraces = true
        showStandardStreams = true
        events("passed", "skipped", "failed", "standardOut", "standardError")
    }
}