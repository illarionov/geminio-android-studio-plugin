import dev.bmac.gradle.intellij.GenerateBlockMapTask
import dev.bmac.gradle.intellij.PluginUploader
import dev.bmac.gradle.intellij.UploadPluginTask
import org.jetbrains.intellij.platform.gradle.extensions.IntelliJPlatformExtension
import org.jetbrains.intellij.platform.gradle.tasks.BuildPluginTask

plugins {
    id("dev.bmac.intellij.plugin-uploader")
}

val buildPluginTaskProvider = tasks.named<BuildPluginTask>("buildPlugin")
val intellijPluginConfig = the<IntelliJPlatformExtension>().pluginConfiguration

tasks.named<GenerateBlockMapTask>("generateBlockMap") {
    dependsOn(buildPluginTaskProvider)
}

tasks.named<UploadPluginTask>("uploadPlugin") {
    url = "https://plugins.pixnews.ru@storage.yandexcloud.net"
    repoType = PluginUploader.RepoType.S3
    downloadUrlPrefix = "https://plugins.pixnews.ru"

    file = buildPluginTaskProvider.flatMap { it.archiveFile }

    pluginName = intellijPluginConfig.name
    pluginId = intellijPluginConfig.id
    version = intellijPluginConfig.version
    pluginDescription = intellijPluginConfig.description
    changeNotes = intellijPluginConfig.changeNotes
    sinceBuild = intellijPluginConfig.ideaVersion.sinceBuild
    untilBuild = intellijPluginConfig.ideaVersion.untilBuild
}
