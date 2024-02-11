package com.tws.moments.ui.adapters

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.data.api.entry.CommentsBean
import com.tws.moments.databinding.ItemCommentBinding
import com.tws.moments.imageloader.GlideImageLoader
import com.tws.moments.imageloader.ImageLoader
import javax.inject.Inject

class CommentsChangedAdapter(private val commentsList: List<CommentsBean>) :
    RecyclerView.Adapter<CommentsChangedAdapter.CommentChangedViewHolder>() {

    @Inject
    lateinit var imageLoader: ImageLoader
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentChangedViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentChangedViewHolder(binding)
    }

    override fun getItemCount(): Int = commentsList.size


    override fun onBindViewHolder(holder: CommentChangedViewHolder, position: Int) {
        holder.bind(commentsList[position])
    }

    inner class CommentChangedViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.movementMethod = LinkMovementMethod.getInstance()
        }

        fun bind(commentsBean: CommentsBean) {
            binding.root.text = commentsBean.content
        }

    }

}