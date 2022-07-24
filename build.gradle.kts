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

    publications {
        register<MavenPublication>("sonatype") {
            from(components["java"])

            pom {
                name.set("More Code")
                description.set("Library containing utilities for working with morse code")
                url.set("https://github.com/jacobtread/MorseCode")

                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://github.com/jacobtread/MorseCode/blob/master/LICENSE.md")
                    }
                }

                developers {
                    developer {
                        id.set("jacobtread")
                        name.set("Jacobtread")
                        email.set("jacobtread@gmail.com")
                    }
                }

                scm {
                    url.set("https://github.com/jacobtread/MorseCode")
                }
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}