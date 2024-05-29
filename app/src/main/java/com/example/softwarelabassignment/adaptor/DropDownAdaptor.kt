package com.example.softwarelabassignment.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.databinding.DropDownAdaptorItemBinding
import com.example.softwarelabassignment.databinding.DropDownListBinding
import com.example.softwarelabassignment.model.StateItem


class DropDownAdaptor : RecyclerView.Adapter<DropDownAdaptor.DropDownViewHolder>() {

    inner class DropDownViewHolder(val binding: DropDownAdaptorItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<StateItem>() {
        override fun areItemsTheSame(oldItem: StateItem, newItem: StateItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: StateItem, newItem: StateItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DropDownViewHolder {
        return DropDownViewHolder(
            DropDownAdaptorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: DropDownViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        val binding = holder.binding
         binding.itemNameTextView.text = currentItem.name
        binding.root.setOnClickListener {
             onItemClickListener?.let {
                 it(currentItem,position)
             }
        }
    }

    private var onItemClickListener: ((StateItem, Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (StateItem, Int) -> Unit) {
        onItemClickListener = listener
    }

}