package com.example.softwarelabassignment.adaptor.onboarding

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.softwarelabassignment.databinding.OnboardingScreenListItemBinding
import com.example.softwarelabassignment.model.onBoarding.OnBoardingDataItem

class OnboardingListAdaptor : RecyclerView.Adapter<OnboardingListAdaptor.OnboardingHolder>() {

    inner class OnboardingHolder(val binding: OnboardingScreenListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // for faster change in view
    private val differCallBack = object : DiffUtil.ItemCallback<OnBoardingDataItem>() {
        override fun areItemsTheSame(
            oldItem: OnBoardingDataItem,
            newItem: OnBoardingDataItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: OnBoardingDataItem,
            newItem: OnBoardingDataItem
        ): Boolean {
            return oldItem == newItem && oldItem.title == newItem.title
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingHolder {

        return OnboardingHolder(
            OnboardingScreenListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: OnboardingHolder, position: Int) {
         val binding = holder.binding
        val currentItem = differ.currentList[position]
        binding.root.setBackgroundColor(Color.parseColor(currentItem.backgroundColor))
        binding.btJoin.setBackgroundColor(Color.parseColor(currentItem.backgroundColor))

        binding.titleTv.text = currentItem.title

        binding.descriptionTv.text = currentItem.description

        Glide
            .with(binding.imageView)
            .load(currentItem.imageResource)
            .fitCenter()
            .into(binding.imageView)

        binding.btLogin.setOnClickListener {
            onLoginClickListener?.let {
                it()
            }
        }
        binding.btJoin.setOnClickListener{
            onJoinClickListener?.let {
                it()
            }
        }
    }

    private var onJoinClickListener: (() -> Unit)? = null

    fun setOnJoinClickListener(listener: () -> Unit) {
        onJoinClickListener = listener
    }

    private var onLoginClickListener: (() -> Unit)? = null

    fun setOnLoginClickListener(listener: () -> Unit) {
        onLoginClickListener = listener
    }


}