package ru.hh.plugins.core_utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

fun Project.isRoot() = project == project.rootProject

val Project.libs: VersionCatalog get() = extensions.getByType<VersionCatalogsExtension>().named("libs")
