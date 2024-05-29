package com.example.softwarelabassignment.adaptor.signUpHours

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.TimeRvListItemBinding
import com.example.softwarelabassignment.model.signUp.HoursListItem


class SignUpHoursListAdaptor :
    RecyclerView.Adapter<SignUpHoursListAdaptor.SignUpHoursViewHolder>() {

    inner class SignUpHoursViewHolder(val binding: TimeRvListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    // for faster change in view
    private val differCallBack = object : DiffUtil.ItemCallback<HoursListItem>() {
        override fun areItemsTheSame(
            oldItem: HoursListItem,
            newItem: HoursListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HoursListItem,
            newItem: HoursListItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignUpHoursViewHolder {
        return SignUpHoursViewHolder(
            TimeRvListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SignUpHoursViewHolder, position: Int) {

    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

}