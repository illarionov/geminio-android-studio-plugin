plugins {
    `kotlin-dsl`
}

group = "ru.hh.plugins.static_analysis"

dependencies {
    implementation(project(":gradle-ext:core-utils"))
    implementation(libs.detekt.gradle.plugin)
    implementation(libs.kotlin.html)
}
