plugins {
    `kotlin-dsl`
}

group = "ru.hh.plugins.build_logic"

dependencies {
    implementation(project(":testing-convention"))
    implementation(project(":kotlin-convention"))
    implementation(project(":gradle-ext:core-utils"))

    implementation(libs.intellij.changelog.plugin)
    implementation(libs.intellij.platform.plugin)
    implementation(libs.intellij.plugin.uploader)
    implementation(libs.kotlin.plugin)
}
