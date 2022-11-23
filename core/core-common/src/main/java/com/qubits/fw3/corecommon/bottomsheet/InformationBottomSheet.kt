package com.qubits.fw3.corecommon.bottomsheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.qubits.fw3.tb.corecommon.databinding.DialogBottomSheetBinding
import com.qubits.fw3.corecommon.extensions.setDebounceClickListener
import com.qubits.fw3.tb.coreui.R


class InformationBottomSheet : BottomSheetDialogFragment() {
    private var _binding: DialogBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var title: String = ""
    private var message: String = ""
    private var negativeButtonText: String = ""
    private var positiveButtonText: String = ""

    private val liveData: MutableLiveData<Boolean> = MutableLiveData()

    fun liveData(): LiveData<Boolean> = liveData

    override fun getTheme(): Int = R.style.RoundedBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            title = it.getString(KEY_TITLE).toString()
            message = it.getString(KEY_MESSAGE).toString()
            negativeButtonText = it.getString(KEY_NEGATIVE_BUTTON_TEXT).toString()
            positiveButtonText = it.getString(KEY_POSITIVE_BUTTON_TEXT).toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        binding.apply {
            tvTitle.text = title
            tvMessage.text = message.also { tvMessage.isVisible = message.isNotEmpty() }
            btnNegative.text =
                negativeButtonText.also { btnNegative.isVisible = negativeButtonText.isNotEmpty() }
            btnPositive.text =
                positiveButtonText.also { btnPositive.isVisible = positiveButtonText.isNotEmpty() }
            btnNegative.setDebounceClickListener {
                liveData.postValue(false)
                this@InformationBottomSheet.dismiss()
            }
            btnPositive.setDebounceClickListener {
                liveData.postValue(true)
                this@InformationBottomSheet.dismiss()
            }
        }
    }

    companion object {

        fun newInstance(
            title: String,
            message: String,
            positiveButtonText: String,
            negativeButtonText: String
        ): InformationBottomSheet =
            InformationBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, title)
                    putString(KEY_MESSAGE, message)
                    putString(KEY_POSITIVE_BUTTON_TEXT, positiveButtonText)
                    putString(KEY_NEGATIVE_BUTTON_TEXT, negativeButtonText)
                }
            }

        private const val KEY_TITLE = "title"
        private const val KEY_MESSAGE = "message"
        private const val KEY_POSITIVE_BUTTON_TEXT = "positive_button_text"
        private const val KEY_NEGATIVE_BUTTON_TEXT = "negative_button_text"
    }

}