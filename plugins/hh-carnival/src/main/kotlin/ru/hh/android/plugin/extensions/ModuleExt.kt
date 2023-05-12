package ru.hh.android.plugin.extensions

import com.intellij.openapi.module.Module
import com.intellij.psi.PsiDirectory
import org.jetbrains.kotlin.idea.core.util.toPsiDirectory

/**
 * Imagine module structure like this:
 *
 * /parentFolder                !!! <-- parent for parent for moduleFile !!!
 *      /my-module-name         <-- root for moduleFile
 *          my-module-name.iml  <-- moduleFile
 */
val Module.moduleParentPsiDirectory: PsiDirectory?
    get() = moduleFile?.parent?.parent?.toPsiDirectory(project)

/**
 * Imagine module structure like this:
 *
 * /parentFolder                <-- parent for parent for moduleFile
 *      /my-module-name         !!! <-- root for moduleFile !!!
 *          my-module-name.iml  <-- moduleFile
 */
val Module.rootPsiDirectory: PsiDirectory?
    get() = moduleFile?.parent?.toPsiDirectory(project)

val Module.relativePathToParent: String
    get() = ".${this.moduleFile?.parent?.parent?.path?.removePrefix("${this.project.basePath}")}"
