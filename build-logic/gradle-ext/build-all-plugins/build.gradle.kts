plugins {
    `kotlin-dsl`
}

group = "ru.hh.plugins.gradle"

dependencies {
    implementation(project(":idea-convention"))
    implementation(project(":gradle-ext:core-utils"))
}

gradlePlugin {
    plugins {
        create("buildAllPlugins") {
            id = "ru.hh.plugins.gradle.build-all-plugins"
            implementationClass = "ru.hh.plugins.gradle.build_all_plugins.BuildAllPluginsGradlePlugin"
            displayName = "Build all plugins and move artifacts into single directory"
        }
    }
}
