package cl.blackmind.aldoapp.ui.home.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import cl.blackmind.aldoapp.R
import cl.blackmind.aldoapp.data.model.response.Response
import cl.blackmind.aldoapp.databinding.FragmentSuccessBinding
import cl.blackmind.aldoapp.ui.viewmodel.SharedViewModel
import com.qubits.fw3.corebase.fragment.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.qubits.fw3.corebase.coroutines.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SuccessFragment : BaseViewBindingFragment<FragmentSuccessBinding>() {

    private val sharedViewModel by viewModel<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSuccessBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.isDoneLiveData.observe(viewLifecycleOwner, ::observeAssignmentEventChanges)
        sharedViewModel.assignment()
    }

    private fun observeAssignmentEventChanges(result: Result<cl.blackmind.aldoapp.data.model.response.Response<Boolean>>?)  {
        when (result) {
            is Result.OnLoading -> {
                //Toast.makeText(activity, "LOADING", Toast.LENGTH_SHORT).show()
            }
            is Result.OnSuccess -> {
                viewBinding!!.progressBar.visibility = View.INVISIBLE
                viewBinding!!.textSuccess.text = "Código Serial y código RFID asociado correctamente"
                sharedViewModel.clean()
                Handler(Looper.getMainLooper()).postDelayed(
                    {

                        findNavController().navigate(R.id.action_successFragment_to_ReadSerialFragment)
                    },
                    3000
                )
            }
            is Result.OnError -> {
                viewBinding!!.progressBar.visibility = View.INVISIBLE
                viewBinding!!.textSuccess.text = "Código Serial o código RFID no disponible"
                /*Handler(Looper.getMainLooper()).postDelayed(
                    {
                        sharedViewModel.clean()
                        findNavController().navigate(R.id.action_successFragment_to_ReadSerialFragment)
                    },
                    3000
                )*/
                sharedViewModel.clean()
                Thread.sleep(3000)
                findNavController().navigate(R.id.action_successFragment_to_ReadSerialFragment)
            }
            is Result.OnCancel -> {
                viewBinding!!.progressBar.visibility = View.INVISIBLE
                sharedViewModel.clean()
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        sharedViewModel.clean()
                        findNavController().navigate(R.id.action_successFragment_to_ReadSerialFragment)
                    },
                    3000
                )
            }
            else -> {
                viewBinding!!.progressBar.visibility = View.INVISIBLE
                sharedViewModel.clean()
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        sharedViewModel.clean()
                        findNavController().navigate(R.id.action_successFragment_to_ReadSerialFragment)
                    },
                    3000
                )
            }
        }
    }
}