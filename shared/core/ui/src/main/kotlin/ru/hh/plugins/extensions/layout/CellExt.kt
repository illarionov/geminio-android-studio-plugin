package ru.hh.plugins.extensions.layout

import com.intellij.ui.SimpleListCellRenderer
import javax.swing.JList

/**
 * Backported version of [com.intellij.ui.dsl.builder.UtilsKt.listCellRenderer] available
 * in IntelliJ Platform since 2023.1
 */
fun <T> listCellRenderer(renderer: SimpleListCellRenderer<T>.(T) -> Unit): SimpleListCellRenderer<T> {
    return object : SimpleListCellRenderer<T>() {
        override fun customize(list: JList<out T>, value: T, index: Int, selected: Boolean, hasFocus: Boolean) {
            // BasicComboBoxUI.getBaseline can try to get renderer for null value even when comboBox doesn't allow
            // nullable elements
            if (index != -1 || value != null) {
                renderer(value)
            }
        }
    }
}
