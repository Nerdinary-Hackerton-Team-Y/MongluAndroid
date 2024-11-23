package com.project.presentation.postdeatil

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.presentation.databinding.ItemRvCommentBinding

class CommentListAdapter
    : ListAdapter<CommentItem, CommentListAdapter.ViewHolder>(CommentDiffUtil()) {
    inner class ViewHolder(
        private val binding: ItemRvCommentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CommentItem) = with(binding) {
            tvItemCommentWriter.text = item.writer
            tvItemCommentComment.text = item.comment
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentListAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CommentListAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class CommentDiffUtil() : DiffUtil.ItemCallback<CommentItem>() {
    override fun areItemsTheSame(oldItem: CommentItem, newItem: CommentItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CommentItem, newItem: CommentItem): Boolean {
        return oldItem.comment == newItem.comment
    }
}