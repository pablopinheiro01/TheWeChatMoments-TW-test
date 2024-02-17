package com.tws.moments.ui.adapters

import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.R
import com.tws.moments.ui.model.Comment
import com.tws.moments.ui.model.Tweet
import com.tws.moments.utils.clickableSpan
import com.tws.moments.utils.inflate

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentsHolder>() {

    var comments: List<Comment>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsHolder {
        return CommentsHolder(parent.inflate(R.layout.item_comment))
    }

    override fun getItemCount(): Int {
        return comments?.size ?: 0
    }

    override fun onBindViewHolder(holder: CommentsHolder, position: Int) {
        comments?.let { listComments ->
            holder.bind(listComments[position])
        }
    }

    inner class CommentsHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val commentTV: TextView = itemView.findViewById(R.id.tv_simple_comment)

        init {
            commentTV.movementMethod = LinkMovementMethod.getInstance()
        }

        fun bind(comment: Comment) {
            val spannableString = comment.sender?.nick?.clickableSpan {
                Toast.makeText(it.context, "${comment.sender.nick} info.", Toast.LENGTH_SHORT).show()
            }
            commentTV.text = spannableString
            commentTV.append(":" + (comment.content ?: ""))
        }
    }
}
