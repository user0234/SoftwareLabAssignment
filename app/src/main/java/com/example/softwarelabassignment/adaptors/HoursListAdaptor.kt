package com.example.softwarelabassignment.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.TimeRvListItemBinding
import com.example.softwarelabassignment.model.signUp.HourSelectedStatus
import com.example.softwarelabassignment.model.signUp.HoursListItem


class HoursListAdaptor : RecyclerView.Adapter<HoursListAdaptor.HoursListViewHolder>() {

    inner class HoursListViewHolder(val binding: TimeRvListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    // for faster change in view
    private val differCallBack = object : DiffUtil.ItemCallback<HoursListItem>() {
        override fun areItemsTheSame(oldItem: HoursListItem, newItem: HoursListItem): Boolean {
            return oldItem.id == newItem.id && oldItem.selectStatus == newItem.selectStatus
        }

        override fun areContentsTheSame(oldItem: HoursListItem, newItem: HoursListItem): Boolean {
            return oldItem == newItem  && oldItem.selectStatus == newItem.selectStatus && oldItem.id == newItem.id
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursListViewHolder {
        return HoursListViewHolder(
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

    override fun onBindViewHolder(holder: HoursListViewHolder, position: Int) {

        val binding = holder.binding
        val item = differ.currentList[position]

        binding.timeTextview.text = item.name

        when(item.selectStatus.ordinal) {
            HourSelectedStatus.Selected.ordinal-> {
                binding.mainItem.setBackgroundDrawable(binding.root.context.resources.getDrawable(R.drawable.day_orange_rounded_background))
            }

            HourSelectedStatus.NotSelected.ordinal-> {
                binding.mainItem.setBackgroundDrawable(binding.root.context.resources.getDrawable(R.drawable.day_grey_rounded_background))
            }

        }

        binding.mainItem.setOnClickListener {
            onItemClickListener?.let {
                it(item)
            }
        }

    }

    private var onItemClickListener: ((HoursListItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (HoursListItem) -> Unit) {
        onItemClickListener = listener
    }

}