plugins {
    java
    jacoco
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.github.roroche.plantuml") version "1.0.2"
    id("com.diffplug.spotless") version "7.0.0.BETA1"
}

group = "com.example"
version = "1.0.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:33.2.1-jre")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

spotless {
    if (!project.hasProperty("skipSpotless")) {
        // Optional: limit format enforcement to just the files changed by this feature branch
        format("misc") {
            // Define the files to apply `misc` to
            target("*.gradle.kts", "*.gradle", ".gitattributes", ".gitignore")

            // Define the steps to apply to those files
            trimTrailingWhitespace()
            indentWithSpaces() // or spaces. Takes an integer argument if you don"t like 4
            endWithNewline()
        }

        java {
            target("src/**/*.java") // Define the target for Java files
            palantirJavaFormat()
            importOrder("java|javax", "board.master", "", "\\#board.master", "\\#")
            removeUnusedImports()
            formatAnnotations()
        }
    }
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

classDiagram {
    packageName = "board.master"
    outputFile = project.file("../docs/backend.plantuml")
}
