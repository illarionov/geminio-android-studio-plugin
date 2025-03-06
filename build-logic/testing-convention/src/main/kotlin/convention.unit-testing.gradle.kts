@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin
import ru.hh.plugins.core_utils.libs

tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    failFast = false

    /**
     * IDEA adds an init script, using it to define if it is an IDE run
     * used in `:test-project`
     */
    systemProperty(
        "isInvokedFromIde",
        gradle.startParameter.allInitScripts.find { it.name.contains("ijtestinit") } != null
    )

    testLogging {
        events("passed", "skipped", "failed")
    }
}

plugins.withType<KotlinBasePlugin> {
    dependencies {
        add(
            "testImplementation",
            provider {
                project.libs.findLibrary("kotest").get().get()
            }
        )
    }
}
