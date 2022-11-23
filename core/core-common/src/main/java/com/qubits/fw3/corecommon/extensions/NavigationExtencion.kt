package com.qubits.fw3.corecommon.extensions

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun View.navigate(direction: NavDirections) {
    this.findNavController().apply {
        currentDestination?.getAction(direction.actionId)?.let { navigate(direction) }
    }
}