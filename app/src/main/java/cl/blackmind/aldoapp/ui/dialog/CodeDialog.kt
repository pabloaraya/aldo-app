package cl.blackmind.aldoapp.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.blackmind.aldoapp.R
import cl.blackmind.aldoapp.databinding.DialogCodeBinding
import cl.blackmind.aldoapp.ui.base.BaseDialog

class CodeDialog(context: Context) : BaseDialog<DialogCodeBinding>() {

    private val liveData: MutableLiveData<AuthAction> = MutableLiveData()
    fun liveData(): LiveData<AuthAction> = liveData

    init {
        _context = context
        _dialog = Dialog(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _binding = DialogCodeBinding.inflate(inflater)
        _dialog?.let { dialogYoutube ->
            dialogYoutube.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialogYoutube.setContentView(_binding!!.root)
            dialogYoutube.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogYoutube.window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogYoutube.setCancelable(false)
        }
        setupFragment()
    }

    private fun setupFragment() {
        _dialog?.let { dialog ->
            dialog.window?.attributes?.windowAnimations = R.style.ZoomAnimation_Window
        }
        _binding!!.apply {

        }

    }

    sealed class AuthAction {
        object Cancel: AuthAction()
        data class Send(val qr: String) : AuthAction()
    }
}