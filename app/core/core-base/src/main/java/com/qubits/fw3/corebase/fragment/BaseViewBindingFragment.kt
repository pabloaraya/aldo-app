package com.qubits.fw3.corebase.fragment


abstract class BaseViewBindingFragment<VB> : BaseFragment() {

    protected var viewBinding: VB? = null

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}