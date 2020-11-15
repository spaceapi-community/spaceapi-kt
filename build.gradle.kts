import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import java.util.Date

version = "0.1.0"
description = "(De)serialization of SpaceAPI types for Kotlin and Java."
group = "spaceapi-community"

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm") version "1.4.10"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    // Use Kotlin serialization library
    kotlin("plugin.serialization") version "1.4.10"

    // Publishing via Bintray
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.5"
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

tasks.jar {
    manifest {
        attributes(mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
        ))
    }
}

val githubUrl = "https://github.com/spaceapi-community/spaceapi-kt"
val licenseIdentifier = "GPL-3.0-or-later"

publishing {
    publications {
        create<MavenPublication>(project.name) {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])

            pom.withXml {
                asNode().apply {
                    appendNode("description", githubUrl)
                    appendNode("name", rootProject.name)
                    appendNode("url", githubUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", licenseIdentifier)
                        appendNode("url", "https://opensource.org/licenses/GPL-3.0")
                        appendNode("distribution", "repo")
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", "dbrgn")
                        appendNode("name", "Danilo Bargen")
                    }
                    appendNode("scm").apply {
                        appendNode("url", githubUrl)
                    }
                }
            }
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true

    setPublications(project.name)

    pkg.apply {
        repo = "maven"
        name = project.name
        userOrg = "spaceapi-community"
        githubRepo = "spaceapi-community/spaceapi-kt"
        vcsUrl = "$githubUrl.git"
        description = description
        setLabels("kotlin", "java", "spaceapi", "serialization")
        setLicenses(licenseIdentifier)
        desc = description
        websiteUrl = githubUrl
        issueTrackerUrl = "$githubUrl/issues"
        githubReleaseNotesFile = "CHANGELOG.md"
        version.apply {
            name = project.version.toString()
            desc = description
            released = Date().toString()
            vcsTag = "v${project.version}"
        }
    }
}
