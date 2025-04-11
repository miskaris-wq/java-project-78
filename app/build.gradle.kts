plugins {
    application
    checkstyle
    jacoco
    id("com.github.ben-manes.versions") version "0.52.0"
}

application {
    mainClass = "hexlet.code.App"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named("build") {
    dependsOn("checkstyleMain")
    dependsOn("checkstyleTest")
}