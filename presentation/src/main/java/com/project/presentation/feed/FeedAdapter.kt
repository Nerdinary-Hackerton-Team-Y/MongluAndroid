package com.project.presentation.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.presentation.databinding.ListItemFeedBinding

class FeedAdapter(
    private val isShowLike: Boolean = true,
    private val callback: FeedAdapterCallback
) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    private val itemList: MutableList<FeedItem> = mutableListOf()

    inner class FeedViewHolder(private val binding: ListItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val clPhoto = binding.clPhoto
        val ivPhoto = binding.ivPhoto
        val ivLike = binding.ivLike
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding =
            ListItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.count()

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            if(isShowLike){
                ivLike.visibility = View.VISIBLE
                ivLike.isSelected = item.isLike
            }else{
                ivLike.visibility = View.GONE
            }
            Glide.with(ivPhoto).load(item.imgUrl).into(ivPhoto)

            clPhoto.setOnClickListener{
                callback.onItemClick()
            }
            ivLike.setOnClickListener {
                callback.onLikeClick()
            }
        }
    }


    fun setItems(list: List<FeedItem>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}


interface FeedAdapterCallback {
    fun onItemClick()
    fun onLikeClick()
}