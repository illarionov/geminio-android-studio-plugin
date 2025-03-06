import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import ru.hh.plugins.core_utils.libs
import ru.hh.plugins.static_analysis.StaticAnalysisExtension

plugins {
    id("io.gitlab.arturbosch.detekt")
}

val detectJvmTarget = provider {
    project.libs.findVersion("java-version").get().toString()
}

fun Detekt.setupCommonDetektSettings() {
    // Common properties
    parallel = true
    autoCorrect = false
    disableDefaultRuleSets = false
    buildUponDefaultConfig = false
    jvmTarget = detectJvmTarget.get()

    // Setup sources for run
    setSource(files(projectDir))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")

    // reports configuration
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(false)
    }
}

val detektAll by tasks.register<Detekt>("detektAll") {
    description = "Runs over whole code base without the starting overhead for each module."

    setupCommonDetektSettings()

    // Configuration
    val staticAnalysisExtension = project.extensions.getByName<StaticAnalysisExtension>("staticAnalysis")

    config.setFrom(staticAnalysisExtension.detekt.configPath)
    baseline.set(staticAnalysisExtension.detekt.baselinePath)
}

val detektFormat by tasks.register<Detekt>("detektFormat") {
    description = "Reformats whole code base."

    setupCommonDetektSettings()
    autoCorrect = true
    disableDefaultRuleSets = true
    buildUponDefaultConfig = true

    // Configuration
    val staticAnalysisExtension = project.extensions.getByName<StaticAnalysisExtension>("staticAnalysis")
    config.setFrom(staticAnalysisExtension.detekt.configPath)
}

val detektProjectBaseline by tasks.register<DetektCreateBaselineTask>("detektProjectBaseline") {
    description = "Overrides current baseline."

    // Setup sources for run
    setSource(files(projectDir))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")

    // Common properties
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    jvmTarget = detectJvmTarget.get()

    // Configuration
    val staticAnalysisExtension = project.extensions.getByName<StaticAnalysisExtension>("staticAnalysis")

    config.setFrom(staticAnalysisExtension.detekt.configPath)
    baseline.set(staticAnalysisExtension.detekt.baselinePath)
}

dependencies {
    add("detekt", project.libs.findLibrary("detekt-cli").get())
    add("detektPlugins", project.libs.findLibrary("detekt-formatting").get())
}
