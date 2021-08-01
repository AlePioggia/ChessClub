/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java project to get you started.
 * For more details take a look at the Java Quickstart chapter in the Gradle
 * User Manual available at https://docs.gradle.org/5.5.1/userguide/tutorial_java_projects.html
 */

plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    application

    id("org.openjfx.javafxplugin") version "0.0.9"
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // This dependency is used by the application.
    // implementation("com.google.guava:guava:27.1-jre")
    implementation("mysql:mysql-connector-java:8.0.15")

    /* for cross-platform jar: */
    runtimeOnly("org.openjfx:javafx-graphics:$javafx.version:win")
    //runtimeOnly("org.openjfx:javafx-graphics:$javafx.version:linux")
    //runtimeOnly("org.openjfx:javafx-graphics:$javafx.version:mac")

    // Use JUnit test framework
    testImplementation("junit:junit:4.12")
}

application {
    // Define the main class for the application
    mainClassName = "src.it.unibo.bd.db.controller.GUIDemo"
}

javafx {
    version = "15"
    modules("javafx.controls", "javafx.fxml", "javafx.swing")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Jar> {
  manifest {
    attributes["Main-Class"] = "src.it.unibo.bd.db.controller.GUIDemo"
  }

  from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
      configurations["runtimeClasspath"].map { if(it.isDirectory) it else zipTree(it) }
    })
}
