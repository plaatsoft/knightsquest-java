plugins {
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("com.gradleup.shadow") version "9.4.2"
    java
    application
}

group = "nl.plaatsoft.knightsquest"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // JavaFX
    implementation("org.openjfx:javafx-controls:25")
    implementation("org.openjfx:javafx-fxml:25")
    implementation("org.openjfx:javafx-media:25")
    implementation("org.openjfx:javafx-graphics:25")

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