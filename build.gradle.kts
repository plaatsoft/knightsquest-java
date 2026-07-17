plugins {
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("com.gradleup.shadow") version "9.5.1"
    `maven-publish`
    java
    application
}

group = "nl.plaatsoft.knightsquest"
version = "1.1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

// Declare properties from gradle.properties
val nexusUrl: String = project.property("nexusUrl") as String
val nexusUsername: String = project.property("nexusUsername") as String
val nexusPassword: String = project.property("nexusPassword") as String

repositories {
    maven {
        url = uri("${nexusUrl}/maven-public/")
        credentials {
            username = nexusUsername
            password = nexusPassword
        }
    }
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "nexus"

            url = uri(
                if (version.toString().endsWith("SNAPSHOT")) {
                    "${nexusUrl}/maven-snapshots/"
                } else {
                    "${nexusUrl}/maven-releases/"
                }
            )

            credentials {
                username = nexusUsername
                password = nexusPassword
            }
        }
    }
}

dependencies {

    // JavaFX
    implementation("org.openjfx:javafx-controls:26.0.1")
    implementation("org.openjfx:javafx-fxml:26.0.1")
    implementation("org.openjfx:javafx-media:26.0.1")
    implementation("org.openjfx:javafx-graphics:26.0.1")

    // JSON
    implementation("org.json:json:20260522")

    // Log4J
    implementation("org.apache.logging.log4j:log4j-core:2.26.0")
    implementation("org.apache.logging.log4j:log4j-api:2.26.0")

    // Junit
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.1.0")
}

application {
    mainClass.set("nl.plaatsoft.knightsquest.Launcher")
}

// Shadow JAR (recommended for easy distribution)
tasks.shadowJar {
    archiveBaseName.set("knightsquest")
    manifest {
        attributes["Main-Class"] = "nl.plaatsoft.knightsquest.Launcher"
    }
}