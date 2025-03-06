import org.jetbrains.intellij.platform.gradle.extensions.intellijPlatform

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
    id("org.jetbrains.intellij.platform.settings") version "2.3.0"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

    repositories {
        mavenCentral()
        maven("https://packages.atlassian.com/maven/repository/public") {
            mavenContent {
                includeGroupAndSubgroups("""com.atlassian""")
            }
        }
        intellijPlatform {
            defaultRepositories()
        }
    }
}

rootProject.name = "hh-android-plugins"

includeBuild("build-logic")

// region Shared modules

// region Shared core modules
include(":shared:core:utils")
include(":shared:core:freemarker")
include(":shared:core:ui")
include(":shared:core:code-modification")
include(":shared:core:models")
include(":shared:core:psi-utils")
include(":shared:core:logger")
include(":shared:core:notifications")
include(":shared:core:android-studio-stubs")
// endregion

// region Shared features
include(":shared:feature:geminio-sdk")
// endregion

// endregion

// Plugins
include(":plugins:hh-carnival")
include(":plugins:hh-garcon")
include(":plugins:hh-geminio")
