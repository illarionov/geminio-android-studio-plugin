import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import ru.hh.plugins.core_utils.libs
import java.nio.file.Path

plugins {
    id("convention.kotlin-jvm")
    id("org.jetbrains.intellij.platform.base")
}

dependencies {
    intellijPlatform {
        val pathToIde = providers.systemProperty("androidStudioPath").get()

        local(pathToIde)

        // XXX: Find a better way to add the bundled version of the plugin to the dependencies
        localPlugin(Path.of(pathToIde, "plugins/android").toString())


        bundledPlugins(
            "com.intellij.java",
            "org.jetbrains.kotlin",
        )
        pluginVerifier()
        testFramework(TestFrameworkType.Platform)

        // Workaround for https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-faq.html#junit5-test-framework-refers-to-junit4
        testRuntimeOnly(project.libs.findLibrary("junit4").get())
    }
}
