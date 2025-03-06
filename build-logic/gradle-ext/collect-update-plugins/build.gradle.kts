plugins {
    `kotlin-dsl`
}

group = "ru.hh.plugins.gradle"

dependencies {
    implementation(project(":idea-convention"))
    implementation(project(":gradle-ext:core-utils"))
    implementation(libs.intellij.platform.plugin)
}

gradlePlugin {
    plugins {
        create("collectUpdatePlugins") {
            id = "ru.hh.plugins.gradle.collect-update-plugins"
            implementationClass = "ru.hh.plugins.gradle.collect_update_plugins.CollectUpdatePluginsXmlGradlePlugin"
            displayName = "Collect updatePlugins.xml file"
        }
    }
}
