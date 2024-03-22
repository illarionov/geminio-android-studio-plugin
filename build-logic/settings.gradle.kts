rootProject.name = "build-logic"

includeBuild("../libraries")
includeBuild("../vendor/plugin_uploader")

include("kotlin-convention")
include("testing-convention")
include("static-analysis-convention")
include("idea-convention")

include(":gradle-ext:core-utils")
include(":gradle-ext:build-all-plugins")
include(":gradle-ext:collect-update-plugins")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
