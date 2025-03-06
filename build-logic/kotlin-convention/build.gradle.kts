plugins {
    `kotlin-dsl`
}

group = "ru.hh.plugins.build_logic"

dependencies {
    implementation(project(":testing-convention"))
    implementation(project(":gradle-ext:core-utils"))
    implementation(libs.kotlin.plugin)
}
