package cl.blackmind.aldoapp.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cl.blackmind.aldoapp.R
import cl.blackmind.aldoapp.databinding.FragmentSignBinding
import com.qubits.fw3.corebase.fragment.BaseViewBindingFragment

class SignInFragment : BaseViewBindingFragment<FragmentSignBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSignBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding?.apply {
            buttonLogin.setOnClickListener {
                if (inputPin.text.toString() == "2006") {
                    findNavController().navigate(R.id.action_signFragment_to_ReadSerialFragment)
                } else {
                    inputLayout.error = "Pin Incorrecto"
                }
            }
            buttonBack.setOnClickListener {
                findNavController().navigate(R.id.action_signFragment_to_ReadSerialFragment)
            }
        }
    }
}