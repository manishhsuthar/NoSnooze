pluginManagement {
    repositories {
        google()               // For Android plugins and Compose
        mavenCentral()         // For most Kotlin libraries
        gradlePluginPortal()   // For Gradle plugins in general
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NoSnooze"
include(":app")