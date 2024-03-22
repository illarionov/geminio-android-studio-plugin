import dev.bmac.gradle.intellij.GenerateBlockMapTask
import dev.bmac.gradle.intellij.PluginUploader
import dev.bmac.gradle.intellij.UploadPluginTask
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.changelog.ChangelogPluginExtension
import org.jetbrains.intellij.IntelliJPluginExtension
import org.jetbrains.intellij.tasks.BuildPluginTask

plugins {
    id("dev.bmac.intellij.plugin-uploader")
}

repositories {
    mavenCentral()
}

val buildPluginTaskProvider = tasks.named<BuildPluginTask>("buildPlugin")
val intellijPluginExtension = the<IntelliJPluginExtension>()
val changelogExtension = the<ChangelogPluginExtension>()

tasks.named<GenerateBlockMapTask>("generateBlockMap") {
    dependsOn(buildPluginTaskProvider)
}

tasks.named<UploadPluginTask>("uploadPlugin") {
    url = "https://plugins.pixnews.ru@storage.yandexcloud.net"
    repoType = PluginUploader.RepoType.S3
    downloadUrlPrefix = "https://plugins.pixnews.ru"

    file = buildPluginTaskProvider.flatMap { it.archiveFile }

    pluginName = intellijPluginExtension.pluginName
    pluginId = provider { project.group.toString() }
    version = changelogExtension.version
    pluginDescription = providers.of(PluginDescriptionValueSource::class.java) {
        parameters.readmeFilePath = project.file("README.md")
    }
    changeNotes = changelogExtension.run {
        getOrNull(project.version.toString()) ?: getLatest()
    }.toHTML()
    sinceBuild = findProperty("pluginSinceBuild")?.toString()
    untilBuild = findProperty("pluginUntilBuild")?.toString()
}
