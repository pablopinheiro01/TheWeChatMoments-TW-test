package com.tws.moments.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.databinding.ItemMomentHeadBinding
import com.tws.moments.databinding.LayoutBaseTweetBinding
import com.tws.moments.imageloader.ImageLoader
import com.tws.moments.ui.model.Tweet
import com.tws.moments.ui.viewholders.HeaderViewHolder
import com.tws.moments.ui.viewholders.TweetViewHolder
import javax.inject.Inject


private const val TYPE_TWEET = 1
private const val TYPE_HEAD = 1000

class MomentsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var tweets: MutableList<Tweet>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var userBean: UserBean? = null
        set(value) {
            field = value
            notifyItemChanged(0)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEAD -> HeaderViewHolder(
                ItemMomentHeadBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            TYPE_TWEET -> TweetViewHolder(
                LayoutBaseTweetBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalStateException("unknown view type $viewType")
        }
    }

    override fun getItemCount(): Int {
        return (tweets?.size ?: 0) + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEAD
        else TYPE_TWEET
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as? HeaderViewHolder)?.bind(userBean)
        } else {
            tweets?.let {
                (holder as? TweetViewHolder)?.bind(it[tweetIndex(position)])
            }
        }
    }

    fun addMoreTweet(tweets: List<Tweet>?) {
        if (tweets.isNullOrEmpty() || this.tweets.isNullOrEmpty()) {
            return
        }
        this.tweets?.let { mutableTweets ->
            val newTweetSize = tweets.size
            val oldTweetsSize = mutableTweets.size
            mutableTweets.addAll(tweets)
            notifyItemRangeInserted(oldTweetsSize + 1, newTweetSize)
        }
    }

    private fun tweetIndex(position: Int) = position - 1
}
