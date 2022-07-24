import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `maven-publish`
    signing
}

val libraryVersion: String by project

group = "com.jacobtread.morse"
version = libraryVersion

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    repositories {
        maven {
            name = "Sonatype"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val sonatypeUser: String? by project
            val sonatypeKey: String? by project
            credentials {
                username = sonatypeUser
                password = sonatypeKey
            }
        }
    }
}

signing {
    useGpgCmd()
}