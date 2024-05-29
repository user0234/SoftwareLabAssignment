package com.example.softwarelabassignment.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.DateRvListItemBinding
import com.example.softwarelabassignment.model.hourModels.DayItemSelectedStatus
import com.example.softwarelabassignment.model.hourModels.DaysDataItem
import com.example.softwarelabassignment.model.signUp.HoursListItem


class DaysListAdaptor : RecyclerView.Adapter<DaysListAdaptor.DaysListViewHolder>() {

    inner class DaysListViewHolder(val binding: DateRvListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    // for faster change in view
    private val differCallBack = object : DiffUtil.ItemCallback<DaysDataItem>() {
        override fun areItemsTheSame(oldItem: DaysDataItem, newItem: DaysDataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DaysDataItem, newItem: DaysDataItem): Boolean {
            return oldItem == newItem  && oldItem.selectedStatus == newItem.selectedStatus && oldItem.id == newItem.id && isHourEqual(oldItem.hourList,newItem.hourList)
        }

    }

    fun isHourEqual(oldList:List<HoursListItem>,newList: List<HoursListItem>):Boolean {
       return oldList.toSet() == newList.toSet()
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysListViewHolder {
        return DaysListViewHolder(
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

    override fun onBindViewHolder(holder: DaysListViewHolder, position: Int) {

        val binding = holder.binding
        val item = differ.currentList[position]

        binding.dayTextValue.text = item.value

        when(item.selectedStatus.ordinal) {
            DayItemSelectedStatus.Current.ordinal-> {
                binding.mainItem.setBackgroundDrawable(binding.root.context.resources.getDrawable(R.drawable.day_orange_rounded_background))
            }

            DayItemSelectedStatus.Not.ordinal-> {
                binding.mainItem.setBackgroundDrawable(binding.root.context.resources.getDrawable(R.drawable.day_stroke_background))

            }
            DayItemSelectedStatus.Yse.ordinal-> {
                binding.mainItem.setBackgroundDrawable(binding.root.context.resources.getDrawable(R.drawable.day_grey_rounded_background))

            }

        }

        if(item.selectedStatus.ordinal == DayItemSelectedStatus.Current.ordinal) {
            onItemSelectedListener?.let {
                it(item)
            }
        }

        binding.mainItem.setOnClickListener {
            onItemClickListener?.let {
                it(item)
            }
        }

    }

    private var onItemClickListener: ((DaysDataItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (DaysDataItem) -> Unit) {
        onItemClickListener = listener
    }

    private var onItemSelectedListener: ((DaysDataItem) -> Unit)? = null

    fun setOnItemSelectedListener(listener: (DaysDataItem) -> Unit) {
        onItemSelectedListener = listener
    }

}