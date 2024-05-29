package com.example.softwarelabassignment.adaptor.signUpHours

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.DateRvListItemBinding
import com.example.softwarelabassignment.model.hourModels.DaysDataItem

class SignUpHoursDaysListAdaptor :
    RecyclerView.Adapter<SignUpHoursDaysListAdaptor.SignUpHoursDaysViewHolder>() {

    inner class SignUpHoursDaysViewHolder(val binding: DateRvListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    // for faster change in view
    private val differCallBack = object : DiffUtil.ItemCallback<DaysDataItem>() {
        override fun areItemsTheSame(
            oldItem: DaysDataItem,
            newItem: DaysDataItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DaysDataItem,
            newItem: DaysDataItem
        ): Boolean {
            return oldItem == newItem && oldItem.id == newItem.id
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
    var currentSelectedItem: Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignUpHoursDaysViewHolder {

        return SignUpHoursDaysViewHolder(
            DateRvListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SignUpHoursDaysViewHolder, position: Int) {

    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

}