package com.qubits.fw3.corecommon.extensions

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.qubits.fw3.corecommon.util.DebounceClickListener

fun View.setDebounceClickListener(onDebounceClick: (View) -> Unit) {
    val safeClickListener = DebounceClickListener {
        onDebounceClick(it)
    }
    setOnClickListener(safeClickListener)
}

@RequiresApi(Build.VERSION_CODES.M)
fun Activity.makeStatusBarTransparent() {
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        statusBarColor = Color.TRANSPARENT
    }
}