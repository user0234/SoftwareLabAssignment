package com.example.softwarelabassignment.adaptor.signUpHours

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.DateRvListItemBinding
import com.example.softwarelabassignment.model.signUp.DaysListItem

class SignUpHoursDaysListAdaptor :
    RecyclerView.Adapter<SignUpHoursDaysListAdaptor.SignUpHoursDaysViewHolder>() {

    inner class SignUpHoursDaysViewHolder(val binding: DateRvListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    // for faster change in view
    private val differCallBack = object : DiffUtil.ItemCallback<DaysListItem>() {
        override fun areItemsTheSame(
            oldItem: DaysListItem,
            newItem: DaysListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DaysListItem,
            newItem: DaysListItem
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

        holder.binding.dayTextValue.text = differ.currentList[position].dayName

        if (differ.currentList[position].daySelectedHours.isNotEmpty()) {
            holder.binding.dayTextValue.setTextColor(holder.binding.root.resources.getColor(R.color.black))

            holder.binding.root.setBackgroundResource(R.drawable.day_grey_rounded_background)
        } else {
            holder.binding.root.setBackgroundResource(R.drawable.day_stroke_background)
            holder.binding.dayTextValue.setTextColor(holder.binding.root.resources.getColor(R.color.dayDoNotHaveTimeBackground))

        }

        if (currentSelectedItem == position) {
            holder.binding.root.setBackgroundResource(R.drawable.day_orange_rounded_background)
        }

        holder.binding.root.setOnClickListener {
            onItemClickListener?.let {
                it(position)
            }
        }

    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

}