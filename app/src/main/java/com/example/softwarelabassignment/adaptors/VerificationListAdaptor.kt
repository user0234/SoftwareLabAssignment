package com.example.softwarelabassignment.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.databinding.AttachmentListItemBinding
import com.example.softwarelabassignment.model.VerificationDataItem


class VerificationListAdaptor : RecyclerView.Adapter<VerificationListAdaptor.VerificationListViewHolder>() {

    inner class VerificationListViewHolder(val binding: AttachmentListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    // for faster change in view
    private val differCallBack = object : DiffUtil.ItemCallback<VerificationDataItem>() {
        override fun areItemsTheSame(oldItem: VerificationDataItem, newItem: VerificationDataItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: VerificationDataItem, newItem: VerificationDataItem): Boolean {
            return oldItem == newItem  && oldItem.name == newItem.name
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerificationListViewHolder {
        return VerificationListViewHolder(
            AttachmentListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: VerificationListViewHolder, position: Int) {

        val binding = holder.binding
        val item = differ.currentList.get(position)

        binding.fileNameTv.text = item.name

    }

    private var onItemClickListener: ((VerificationDataItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (VerificationDataItem) -> Unit) {
        onItemClickListener = listener
    }

}