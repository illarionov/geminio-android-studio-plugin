plugins {
    `kotlin-dsl`
}

group = "ru.hh.plugins.build_logic"

dependencies {
    implementation(libs.kotlin.plugin)
    implementation(project(":gradle-ext:core-utils"))
}
