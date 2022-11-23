package com.qubits.fw3.corebase.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<D, VH : BaseViewHolder<D>> : RecyclerView.Adapter<VH>() {

    val data = mutableListOf<D>()

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int = data.size

    protected fun getItem(position: Int) = data[position]

    open fun setItems(newItems: List<D>) {
        data.clear()
        data.addAll(newItems)
        notifyDataSetChanged()
    }
}