package cl.blackmind.coreui.attribute

import android.graphics.drawable.Drawable

data class AttrsErrorView(
    val title: String = "",
    val message: String = "",
    val primaryButtonName: String = "",
    val secondaryButtonName: String = "",
    val icon: Drawable? = null,
    val primaryButtonListener: () -> Unit = {},
    val secondaryButtonListener: () -> Unit = {}
)