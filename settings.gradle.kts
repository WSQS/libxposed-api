pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.library") version "9.0.1"
        id("com.android.application") version "9.0.1"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://artifactory.appodeal.com/appodeal-public/")
    }
}

rootProject.name = "libxposed-api"

include(":api", ":checks")
include(":libxposed_example")
include(":xposed_example")
