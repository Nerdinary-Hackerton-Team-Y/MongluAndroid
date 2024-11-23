package com.project.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.presentation.databinding.ListItemHonorRankBinding

class HonorRankAdapter(
    private val callback: HonorRankAdapterCallback
) : RecyclerView.Adapter<HonorRankAdapter.HonorViewHolder>() {
    private val itemList: MutableList<HonorRankItem> = mutableListOf()

    inner class HonorViewHolder(private val binding: ListItemHonorRankBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val clItem = binding.clItem
        val tvTitle = binding.tvTitle
        val ivLike = binding.ivLike
        val tvLikeCount = binding.tvLikeCount
        val tvNickname = binding.tvNickname
        val ivPhoto = binding.ivPhoto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HonorViewHolder {
        val binding =
            ListItemHonorRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HonorViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.count()

    override fun onBindViewHolder(holder: HonorViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            tvNickname.text = item.nickname
            tvTitle.text = item.title
            tvLikeCount.text = item.likeCount.toString()
            Glide.with(ivPhoto).load(item.imgUrl).into(ivPhoto)

            clItem.setOnClickListener{
                callback.onItemClick()
            }
            ivLike.setOnClickListener {
                callback.onLikeClick()
            }
        }
    }

    fun setItems(list: List<HonorRankItem>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}

interface HonorRankAdapterCallback {
    fun onItemClick()
    fun onLikeClick()
}