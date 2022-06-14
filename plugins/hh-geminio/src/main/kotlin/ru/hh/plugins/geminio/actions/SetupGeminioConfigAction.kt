package ru.hh.plugins.geminio.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.options.ShowSettingsUtil
import ru.hh.plugins.geminio.config.editor.GeminioPluginSettingsSearchableConfigurable

class SetupGeminioConfigAction : AnAction() {

    init {
        with(templatePresentation) {
            text = "Setup Config"
            isEnabledAndVisible = true
        }
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        ShowSettingsUtil.getInstance()
            .editConfigurable(project, GeminioPluginSettingsSearchableConfigurable(project))
    }

}
