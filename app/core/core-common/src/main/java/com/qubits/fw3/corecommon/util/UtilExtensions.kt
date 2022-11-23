package com.qubits.fw3.corecommon.util

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.util.DisplayMetrics
import android.widget.TextView

fun convertDpToPixel(dp: Float, context: Context?): Float {
    return if (context != null) {
        val resources = context.resources
        val metrics = resources.displayMetrics
        dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    } else {
        val metrics = Resources.getSystem().displayMetrics
        dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}

fun TextView.setTextHtmlByVersion(text: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.text = Html.fromHtml(text, 0)
    }//
    else {
        this.text = Html.fromHtml(text)
    }
}