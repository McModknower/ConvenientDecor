import java.nio.file.Files
import java.nio.file.Path

plugins {
  kotlin("jvm") version "1.8.21"
  id("application")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
  implementation(files("libs/tada.jar"))
  implementation(files("libs/tada.jar"))
  implementation("com.google.code.gson:gson:2.10.1")
}

kotlin {
  jvmToolchain(11)
}

sourceSets {
  main {
    kotlin {
      srcDir("libs/tada-sources.jar")
    }
  }
}

application {
  mainClass.set("datagen.MainKt")
}

fun deleteDir(directory: Path) {
  Files.walk(directory)
    .sorted(Comparator.reverseOrder())
    .map { it.toFile() }
    .forEach { it.delete() }
}

val generatedDir = projectDir.resolve("../src/main/generated")
val langDir = projectDir.resolve("../src/main/resources/assets/convenientdecor/lang")
val langHelperDir = projectDir.resolve("../lang")
tasks {
  withType<JavaExec> {
    doFirst {
      try {
        deleteDir(generatedDir.toPath())
      } catch (_: Exception) {
        println("Datagen output directory hasn't been generated yet")
      }
    }
    args = listOf(generatedDir.path, langDir.path, langHelperDir.path)
  }
}