import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.6.20-M1"
}

group = "nl.bjornvanderlaan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.1.0")
    testImplementation("io.kotest:kotest-framework-datatest:5.1.0")
    testImplementation("io.kotest:kotest-property:5.1.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}