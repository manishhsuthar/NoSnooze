pluginManagement {
    repositories {
        google()               // For Android plugins and Compose
        mavenCentral()         // For most Kotlin libraries
        gradlePluginPortal()   // For Gradle plugins in general
    }
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