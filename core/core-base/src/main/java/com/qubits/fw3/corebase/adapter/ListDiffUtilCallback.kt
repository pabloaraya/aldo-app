package com.qubits.fw3.corebase.adapter

import androidx.recyclerview.widget.DiffUtil
import java.lang.Exception

class ListDiffUtilCallback (
    private var oldItems: List<Any>,
    private var newItems: List<Any>,
    private val fieldName: String
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return try {
            val newItem: String? =
                oldItems[oldItemPosition].javaClass.getDeclaredField(fieldName).toString()
            val oldItem: String? =
                oldItems[oldItemPosition].javaClass.getDeclaredField(fieldName).toString()
            newItem?.isNotEmpty() == oldItem?.isNotEmpty()
        } catch (e: Exception) {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return Change(oldItem, newItem)
    }
}