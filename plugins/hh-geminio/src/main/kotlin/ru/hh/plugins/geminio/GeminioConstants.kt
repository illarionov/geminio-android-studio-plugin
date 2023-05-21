package ru.hh.plugins.geminio

object GeminioConstants {

    const val DEFAULT_NOTIFICATIONS_TITLE = "Geminio"
    const val DEFAULT_MODIFY_ROOT_SETTINGS_GRADLE_FILE = true

    /**
     * Duplicates groupId in plugin.xml
     */
    const val HH_TEMPLATES_NEW_GROUP_ID = "ru.hh.plugins.geminio.New.HHTemplatesGroup"
    const val HH_TEMPLATES_GENERATE_GROUP_ID = "ru.hh.plugins.geminio.Generate.HHTemplatesGroup"
    const val HH_MODULES_TEMPLATES_NEW_GROUP_ID = "ru.hh.plugins.geminio.New.HHModulesTemplatesGroup"
    const val HH_MODULES_TEMPLATES_GENERATE_GROUP_ID = "ru.hh.plugins.geminio.Generate.HHModulesTemplatesGroup"

    const val GEMINIO_TEMPLATE_CONFIG_FILE_NAME = "recipe.yaml"

    object RecentsKeys {
        private const val BASE = "ru.hh.android.plugins.geminio"

        const val MODIFY_ROOT_SETTINGS_GRADLE_FILE = "$BASE.modify_root_settings_gradle_file"
    }

}
