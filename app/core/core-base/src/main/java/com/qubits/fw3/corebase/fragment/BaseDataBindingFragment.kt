package com.qubits.fw3.corebase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDataBindingFragment<V : ViewDataBinding> : BaseFragment() {

    lateinit var binding: V

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this@BaseDataBindingFragment
        return binding.root
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}