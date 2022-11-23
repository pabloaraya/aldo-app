package cl.blackmind.aldoapp.ui.base

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri


@Suppress("PropertyName")
abstract class BaseDialog<VB> {
    var _dialog : Dialog? = null

    var _context: Context? = null

    var _binding: VB? = null

    fun show() = _dialog?.show()

    fun dismiss() {
        _binding = null
        _dialog?.dismiss()
    }

    fun goToWeb(url: String) {
        _context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}