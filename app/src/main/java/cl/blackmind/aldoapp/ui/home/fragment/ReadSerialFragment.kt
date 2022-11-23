package cl.blackmind.aldoapp.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cl.blackmind.aldoapp.Constants
import cl.blackmind.aldoapp.R
import cl.blackmind.aldoapp.databinding.FragmentReadSerialBinding
import cl.blackmind.aldoapp.ui.viewmodel.SharedViewModel
import com.qubits.fw3.corebase.fragment.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReadSerialFragment : BaseViewBindingFragment<FragmentReadSerialBinding>() {

    private val sharedViewModel by viewModel<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentReadSerialBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding?.apply {
            buttonClean.setOnClickListener {
                sharedViewModel.clean()
            }
            buttonAdmin.setOnClickListener {
                sharedViewModel.clean()
                findNavController().navigate(R.id.action_ReadSerialFragment_to_SignInFragment)
            }
        }
        observeReadyEventChanges()
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.setCurrentDevice(cl.blackmind.aldoapp.Constants.Device.SERIAL)
    }

    private fun observeReadyEventChanges() {
        sharedViewModel.getLastSerial().observe(viewLifecycleOwner) {
            if(sharedViewModel.getLastSerial().value != "") {
                findNavController().navigate(R.id.action_ReadSerialFragment_to_readRfidFragment)
            }
        }
    }
}