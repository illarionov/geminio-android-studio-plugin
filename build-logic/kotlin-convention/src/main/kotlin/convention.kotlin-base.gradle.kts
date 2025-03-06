import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import ru.hh.plugins.core_utils.libs

plugins {
    id("convention.unit-testing")
}

/**
 * Exists because `compile` task is ambiguous in projects with jvm and android modules combined
 */
val compileAllTask: TaskProvider<Task> = tasks.register("compileAll") {
    description = "Compiles all available modules in all variants"

    dependsOn(tasks.withType<KotlinCompile>())
}

val targetJavaVersion = provider { project.libs.findVersion("java-version").get().toString() }

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(targetJavaVersion.get())
        allWarningsAsErrors = false
        optIn.add("kotlin.RequiresOptIn")
        freeCompilerArgs.add("-Xjvm-default=all-compatibility")
    }
}

plugins.withType<JavaBasePlugin> {
    afterEvaluate {
        tasks.withType<JavaCompile> {
            sourceCompatibility = targetJavaVersion.get()
            targetCompatibility = targetJavaVersion.get()
        }
    }

    dependencies {
        add(
            "implementation",
            provider { project.libs.findLibrary("kotlin-stdlib").get().get() }
        )
    }
}

