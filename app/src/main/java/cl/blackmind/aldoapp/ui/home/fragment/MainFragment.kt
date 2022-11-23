package cl.blackmind.aldoapp.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cl.blackmind.aldoapp.R
import cl.blackmind.aldoapp.databinding.FragmentMainBinding
import com.qubits.fw3.corebase.fragment.BaseViewBindingFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : BaseViewBindingFragment<FragmentMainBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding?.apply {
            buttonOpe.setOnClickListener {
                findNavController().navigate(R.id.action_MainFragment_to_readRfidFragment)
            }
            buttonAdmin.setOnClickListener {
                findNavController().navigate(R.id.action_MainFragment_to_signFragment)
            }
        }
    }
}