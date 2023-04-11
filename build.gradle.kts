import java.net.URI
import java.util.Properties
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

version = "0.3.0"
description = "(De)serialization of SpaceAPI types for Kotlin and Java."
group = "io.github.spaceapi-community"

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm") version "1.8.10"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    // Use Kotlin serialization library
    kotlin("plugin.serialization") version "1.8.10"

    // Publishing via Maven Central
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    // Use serialization library
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    // Kotlin helper libraries
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // HTTP client for DirectoryParser
    testImplementation("com.github.kittinunf.fuel:fuel:2.3.1")
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

// A task that writes a `project.properties` file, so that code can determine its own version and other information.
tasks.create("createProperties") {
    dependsOn("processResources")
    doLast {
        val file = File("$buildDir/resources/main/project.properties")
        file.parentFile.mkdirs()
        file.writer().use {
            w ->
                val p = Properties()
                p["projectName"] = project.name
                p["projectVersion"] = project.version.toString()
                p.store(w, "Project information generated by gradle `createProperties` task")
        }
    }
}

tasks.named("classes") {
    dependsOn("createProperties")
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

    repositories {
        maven {
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots"
            url = URI(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            if (project.hasProperty("ossrhUsername") && project.hasProperty("ossrhPassword")) {
                credentials {
                    username = project.property("ossrhUsername") as String?
                    password = project.property("ossrhPassword") as String?
                }
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications[project.name])
}
