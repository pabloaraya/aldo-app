package com.qubits.fw3.corecommon.extensions

import android.content.Context
import androidx.core.content.ContextCompat

fun Int.asColor(context: Context) = ContextCompat.getColor(context, this)
fun Int.asDrawable(context: Context) = ContextCompat.getDrawable(context, this)
fun Int.asString(context: Context) = context.resources.getString(this)