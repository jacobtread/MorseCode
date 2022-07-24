pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
    }
    repositories {
        mavenCentral()
    }
}

rootProject.name = "morse-code"

